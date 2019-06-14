package sj;

public class Table {
    private String moduleID="";
    private String packAge="";
    private String name="";
    private String comment="";
    private String sealFlag="";
    
    public Table(){}
    
    public Table(String moduleID, String packAge, String name, String comment, String sealFlag) {
        super();
        this.moduleID = moduleID;
        this.packAge = packAge;
        this.name = name;
        this.comment = comment;
        this.sealFlag = sealFlag;
    }
    
    public String getSealFlag() {
        return sealFlag;
    }
 
    public void setSealFlag(String sealFlag) {
        this.sealFlag = sealFlag;
    }

    public String getModuleID() {
        return moduleID;
    }
    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }
    public String getPackAge() {
        return packAge;
    }
    public void setPackAge(String packAge) {
        this.packAge = packAge;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    
}
