package com.it326;

public class TreeContainer {
    private ScheduleManager m;
    private Account a;
    private Schedule s;
    private Semester sem;
    private Course c;

    public TreeContainer(ScheduleManager sm){
        m = sm;
    }

    public TreeContainer(Account ac){
        a = ac;
    }

    public TreeContainer(Schedule sc){
        s = sc;
    }

    public TreeContainer(Semester s){
        sem = s;
    }

    public TreeContainer(Course cc){
        c = cc;
    }

    public Object getObj(){
        if(a!=null)
            return a;
        if(s!=null)
            return s;
        if(sem!=null)
            return sem;
        if(c!=null)
            return c;
        return null;
    }
}
