package NAD;//must be in a package

public class student{

 private String std_number, reg_number, course;
 private int supervisor_id, placement_id;

 public String getStd_number(){
   return std_number;
 }
 public String getReg_number(){
   return reg_number;
 }
 public String getCourse(){
   return course;
 }
 public int getSupervisor_id(){
   return supervisor_id;
 }
 public int getPlacement_id(){
     return placement_id;
 }
   public void setStd_number(String m){
    this.std_number = m;
}
   public void setReg_number(String m){
    this.reg_number = m;
}
   public void setCourse(String m){
    this.course = m;
}
   public void setSupervisor_id(int m){
    this.supervisor_id = m;
}
    public void setPlacement_id(int m){
        this.placement_id = m;
}
   //public double netSalary(double m){return (m-0.3*(m-(0.05*m)-250000.0));}  
}