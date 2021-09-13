package com.banet.ilooker.model;

import java.io.Serializable;

public abstract class MenuListObject implements Serializable {

    public  String ListSeq ="";

    public String ListNo ="";

    public String ListTitle ="";

    public String ListStartDate = "";

    public String ListEndDate = "";

    public String ListContent = "";

    public abstract void setMenuListField();




}
