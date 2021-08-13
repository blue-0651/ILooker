package com.banet.ilooker.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skt.Tmap.TMapTapi;
import com.skt.Tmap.TMapView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

import kst.macaron.chauffeur.MacaronApp;
import kst.macaron.chauffeur.R;
import kst.macaron.chauffeur.activity.AllocDetailActivity;
import kst.macaron.chauffeur.activity.MainActivity;
import kst.macaron.chauffeur.common.Global;
import kst.macaron.chauffeur.fragment.CustomerLoadFragment;
import kst.macaron.chauffeur.fragment.DestArrivedFragment;
import kst.macaron.chauffeur.fragment.InputPayFragment;
import kst.macaron.chauffeur.fragment.OrgArrivedFragment;
import kst.macaron.chauffeur.model.AllocationSchedule;
import kst.macaron.chauffeur.net.DataInterface;
import kst.macaron.chauffeur.net.ResponseData;
import kst.macaron.chauffeur.utility.DateUtils;
import kst.macaron.chauffeur.utility.Logger;
import kst.macaron.chauffeur.utility.MacaronCustomDialog;
import kst.macaron.chauffeur.utility.PrefUtil;


public class DriveScheduleAdapter extends RecyclerView.Adapter<DriveScheduleAdapter.ScheduleItemViewHolder> {

    private ArrayList<AllocationSchedule> mList;
    private WeakReference<MainActivity> activityWeakReference;
    protected LinearLayout ll_item;
    private boolean allocatedCheck;  // Default: false


    public class ScheduleItemViewHolder extends RecyclerView.ViewHolder {
        protected TextView scheduleTime;
        protected TextView depature;
        protected TextView destination;
        protected TextView supplyExist;
        protected TextView imgNum;
        protected TextView beforeSchedule;

        public ScheduleItemViewHolder(View view) {
            super(view);
            this.scheduleTime = view.findViewById(R.id.schedule_time);
            this.beforeSchedule = view.findViewById(R.id.before_schedule_time);
            this.depature = view.findViewById(R.id.departure);
            this.destination = view.findViewById(R.id.destination);
            this.supplyExist = view.findViewById(R.id.supply);
            this.imgNum = view.findViewById(R.id.schedule_num);
        }
    }


    public DriveScheduleAdapter(Context context, ArrayList<AllocationSchedule> list) {
        activityWeakReference = new WeakReference<>((MainActivity)context);
        this.mList = list;
        allocatedCheck = false;
    }


    // RecyclerView에 새로운 데이터를 보여주기 위해 필요한 ViewHolder를 생성해야 할 때 호출됩니다.
    @Override
    public ScheduleItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.schedule_item, viewGroup, false);

        ScheduleItemViewHolder viewHolder = new ScheduleItemViewHolder(view);
        ll_item = view.findViewById(R.id.ll_schedule_item);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleItemViewHolder scheduleItem, int i) {
        final int pos = i;
        scheduleItem.imgNum.setText(String.valueOf(i + 1));
        scheduleItem.scheduleTime.setText(getReserveTimeIn("HH:mm", mList.get(i).resvDatetime));
        scheduleItem.beforeSchedule.setText(getDifferenceFromResvTime(mList.get(i).resvDatetime) );
        scheduleItem.depature.setText( "출발지: " +mList.get(i).resvOrgPoi);
        scheduleItem.destination.setText( "도착지: " + mList.get(i).resvDstPoi);

        if(mList.get(i).serviceCount > 0) {
            scheduleItem.supplyExist.setVisibility(View.VISIBLE);
        } else {
            scheduleItem.supplyExist.setVisibility(View.INVISIBLE);
        }

        if(!mList.get(pos).allocationStatus.equalsIgnoreCase("ALLOCATED") && !allocatedCheck) {
            // 제일 처음 들어오는 항목이고, 배차상태가 ALLOCATED가 아닐경우 Idx를 요청하고나서 각 상태별 화면으로 이동시킴
            getAllocDetail(mList.get(pos).allocationIdx, mList.get(pos).allocationStatus);
        }

        ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAllocationStatus(mList.get(pos).allocationStatus, pos);
            }
        });
    }

    private void checkAllocationStatus(String status, int position) {
        switch (status) {
            case "ALLOCATED":
                Intent intent = new Intent(activityWeakReference.get(), AllocDetailActivity.class);
                intent.putExtra("a_detail", mList.get(position).allocationIdx);
                intent.putExtra("a_title", "예약현황");
                intent.putExtra("a_flags", true);
                activityWeakReference.get().startActivityForResult(intent, MainActivity.ORG_ARRIVED);
                break;

            default:
                getAllocDetail(mList.get(position).allocationIdx, status);
                break;
        }
    }

    private String getDifferenceFromResvTime(long resvTime) {
        return beforeTimeMillisToHourMinute(resvTime, System.currentTimeMillis());
    }

    /*
     * 현재시간과의 차이를 "시간:분"으로 나타내준다.
     */
    public static String beforeTimeMillisToHourMinute(long date1, long dateCurr) {

        if (date1 < dateCurr)
            return "";
        int diffInHour = 0;
        int diffnMin = 0;

        diffInHour = (int) ((date1 - dateCurr) / (1000 * 60 * 60 ));
        int remaining = (int) ((date1 - dateCurr) % (1000 * 60 * 60 ));
        diffnMin = remaining / (1000 * 60 );

        return "(" + String.valueOf(diffInHour) + ":" + String.valueOf(diffnMin)  + "분전)" ;
    }


    private String getReserveTimeIn(String dateFormat, long reserTimeinMillis) {
        return DateUtils.getTime(dateFormat, reserTimeinMillis);
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }


    private void getAllocDetail(long alloc_idx, final String status) {
        allocatedCheck = true;
        HashMap<String, Object> params = new HashMap<>();
        params.put("allocationIdx", alloc_idx);
        DataInterface.getInstance().getAllocDetail(activityWeakReference.get(), params, new DataInterface.ResponseCallback<ResponseData<AllocationSchedule>>() {
            @Override
            public void onSuccess(ResponseData<AllocationSchedule> response) {
                if ("S000".equals(response.getResultCode())) {
                    MacaronApp.currAllocation = response.getData();
                    setActionOfStatus(status);

                } else {
                    Logger.i("<PHD>", "예약상세 receive 실패");
                }
            }

            @Override
            public void onError(ResponseData<AllocationSchedule> response) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private MacaronCustomDialog macaronCustomDialog;

    /**
     * 배차상태별 행동처리
     * @param status 배차상태
     */
    private void setActionOfStatus(String status) {
        switch (status) {
            case "DEPART":
                TMapView mMapView = new TMapView(activityWeakReference.get());
                mMapView.setSKTMapApiKey(Global.instance().TMAP_APIKEY);

                TMapTapi tMapTapi = new TMapTapi(activityWeakReference.get());

                if (MacaronApp.currAllocation != null) {
                    if(tMapTapi.isTmapApplicationInstalled()) {
                        activityWeakReference.get().GoNativeScreenAdd(new OrgArrivedFragment(), null);
                        tMapTapi.invokeRoute(MacaronApp.currAllocation.resvOrgPoi, (float) MacaronApp.currAllocation.resvOrgLon, (float) MacaronApp.currAllocation.resvOrgLat);
                    } else {
                        ArrayList result = tMapTapi.getTMapDownUrl();
                        if(result != null) {
                            showTmapNotInstallPopup(result);
                        }
                    }
                } else {
                    Toast.makeText(activityWeakReference.get(), "해당 배차정보가 없습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
            case "ORIGIN":
                activityWeakReference.get().GoNativeScreenAdd(new CustomerLoadFragment(), null);
                break;
            case "CHECKIN":
                if(PrefUtil.getStartTime(activityWeakReference.get()) == 0) {
                    PrefUtil.setStartTime(activityWeakReference.get(), System.currentTimeMillis());
                }

                TMapView mMapView_ = new TMapView(activityWeakReference.get());
                mMapView_.setSKTMapApiKey(Global.instance().TMAP_APIKEY);

                TMapTapi tMapTapi_ = new TMapTapi(activityWeakReference.get());

                if (MacaronApp.currAllocation != null) {
                    if(tMapTapi_.isTmapApplicationInstalled()) {
                        activityWeakReference.get().GoNativeScreenAdd(new DestArrivedFragment(), null);
                        tMapTapi_.invokeRoute(MacaronApp.currAllocation.resvOrgPoi, (float) MacaronApp.currAllocation.resvOrgLon, (float) MacaronApp.currAllocation.resvOrgLat);
                    } else {
                        ArrayList result = tMapTapi_.getTMapDownUrl();
                        if(result != null) {
                            showTmapNotInstallPopup(result);
                        }
                    }
                } else {
                    Toast.makeText(activityWeakReference.get(), "해당 배차정보가 없습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
            case "ARRIVAL":
                switchToPayment(MacaronApp.currAllocation.fareCat);
                break;
        }
    }

    private void showTmapNotInstallPopup(final ArrayList result) {
        macaronCustomDialog = new MacaronCustomDialog(
                activityWeakReference.get(),
                null,
                activityWeakReference.get().getString(R.string.tmap_not_install),
                "네",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        macaronCustomDialog.dismiss();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(result.get(0).toString()));
                        activityWeakReference.get().startActivity(intent);
                    }
                },
                true);
        macaronCustomDialog.show();
    }

    private void switchToPayment(String payCat) {
        Bundle bundle = new Bundle();
        switch (payCat) {
            case "APPCARD":
                bundle.putString("arrowBack", "y");
                activityWeakReference.get().GoNativeScreenAdd(new InputPayFragment(), bundle);
                break;
            case "OFFLINE":
                bundle.putString("arrowBack", "y");
                activityWeakReference.get().GoNativeScreenAdd(new InputPayFragment(), bundle);
                break;
            default:
                Toast.makeText(activityWeakReference.get(), "결제수단이 존재하지 않습니다." ,Toast.LENGTH_SHORT).show();
        }
    }


}
