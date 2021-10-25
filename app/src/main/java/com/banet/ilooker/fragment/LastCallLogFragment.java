package com.banet.ilooker.fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.View;

import com.banet.ilooker.R;
import com.banet.ilooker.activity.MainActivity;
import com.banet.ilooker.adapter.LastCallLogAdapter;
import com.banet.ilooker.databinding.FragmentLastCallLogBinding;
import com.banet.ilooker.model.RecentCallLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class LastCallLogFragment extends BaseBindingFragment<FragmentLastCallLogBinding>  {
    protected String TAG = getClass().getSimpleName();
    List<RecentCallLog> recentCallLogList = new ArrayList<>();
    LastCallLogAdapter lastCallLogAdapter ;
    public LastCallLogFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
          

        }
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_last_call_log, container, false);
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_last_call_log;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        showProgress("잠시 기다리세요");
        lastCallLogAdapter = new LastCallLogAdapter(getActivity(), getCallLog()) ;
        getBinding().rvRecentLogNumber.setAdapter(lastCallLogAdapter);

        getBinding().searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<RecentCallLog> tempSearchList = new   ArrayList<RecentCallLog>();
                if(getBinding().etSearchCond.getText().toString().trim().equals("")){
                    lastCallLogAdapter = new LastCallLogAdapter(getActivity(), recentCallLogList);
                    getBinding().rvRecentLogNumber.setAdapter(lastCallLogAdapter);
                    lastCallLogAdapter.notifyDataSetChanged();
                }else {
                    for (int i = 0; recentCallLogList.size() > i; i++) {
                        if (recentCallLogList.get(i).phoneNumber.contains(getBinding().etSearchCond.getText().toString().trim())) {
                            tempSearchList.add(recentCallLogList.get(i));
                        }
                    }

                    lastCallLogAdapter = new LastCallLogAdapter(getActivity(), tempSearchList);
                    getBinding().rvRecentLogNumber.setAdapter(lastCallLogAdapter);
                    lastCallLogAdapter.notifyDataSetChanged();
                }
            }
        });

        ( (MainActivity)getActivity()).setBottomTabBarVisible(true);
        hideProgress();

       // readSMSMessage();
    }
 //   Uri allCalls = Uri.parse("content://call_log/calls");
 //   String strOrder = android.provider.CallLog.Calls.DATE + " DESC";
// Cursor managedCursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, android.provider.CallLog.Calls.DATE + " BETWEEN ? AND ?", whereValue, strOrder);
    private List<RecentCallLog>  getCallLog() {
        recentCallLogList = new ArrayList<>();

        StringBuffer sb = new StringBuffer();
        Cursor managedCursor = getActivity().managedQuery(CallLog.Calls.CONTENT_URI, null,
                null, null, null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        int cachedName = managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);

        sb.append("Call Details :");
        while (managedCursor.moveToNext()) {
            String  phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "HH:mm");
            String dateString = formatter.format(new Date(Long
                    .parseLong(callDate)));
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);
            String cachedNameString = managedCursor.getString(cachedName);
            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;

            }

            sb.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- "
                    + dir + " \nCall Date:--- " + callDayTime
                    + " \nCall duration in sec :--- " + callDuration);
            sb.append("\n----------------------------------");
            recentCallLogList.add(new RecentCallLog(phNumber,"001", callType, cachedNameString, "", dateString, ""));
        }
        managedCursor.close();
   //     Log.d(TAG,  sb.toString() );
        return recentCallLogList;
    }

    public int readSMSMessage() {
        Uri allMessage =  Uri.parse("content://sms");
        ContentResolver cr = getActivity().getContentResolver();
        Cursor c = cr.query(allMessage, new String[] { "_id", "thread_id", "address", "person", "date", "body" }, null, null, "date DESC");

        String string = "";
        int count = 0;
        while (c.moveToNext()) {
            long messageId = c.getLong(0);
            long threadId = c.getLong(1);
            String address = c.getString(2);
            long contactId = c.getLong(3);
            String contactId_string = String.valueOf(contactId);
            long timestamp = c.getLong(4);
            String body = c.getString(5);

            string = String.format("msgid:%d, threadid:%d, address:%s, " + "contactid:%d, contackstring:%s, timestamp:%d, body:%s", messageId, threadId, address, contactId,
                    contactId_string, timestamp, body);

            Log.d(TAG, ++count + "st, Message: " + body);
        }

        return 0;
    }

}