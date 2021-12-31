package pkg.handbook.domain;

public class Bill {
    private int id;
    private double money;
    private String label;
    private String comment;
    private String calendar;
    private int ctgr;
    private int userid;

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public double getMoney(){
        return this.money;
    }
    public void setMoney(double money){
        this.money = money;
    }

    public String getLabel(){
        return this.label;
    }
    public void setLabel(String label){
        this.label = label;
    }

    public String getComment(){
        return this.comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }

    public String getCalendar(){
        return this.calendar;
    }
    public void setCalendar(String calendar){
        this.calendar = calendar;
    }

    public int getCtgr(){
        return this.ctgr;
    }
    public void setCtgr(int ctgr){
        this.ctgr = ctgr;
    }

    public int getUserid(){
        return this.userid;
    }
    public void setUserid(int userid){
        this.userid = userid;
    }
}
