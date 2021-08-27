package Test;

public class Employee {

    private String EmployeeID;
    private String Name;
    private String Address;
    private String Department;
    private String Designation;

    public Employee(String EmployeeID, String Name, String Address, String Department, String Designation) {
        this.EmployeeID = EmployeeID;
        this.Name = Name;
        this.Address = Address;
        this.Department = Department;
        this.Designation = Designation;
    }

    public Employee() {
    }

    
    
    
    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    @Override
    public String toString() {
        return "Employee{" + "EmployeeID=" + EmployeeID + ", Name=" + Name + ", Address=" + Address + ", Department=" + Department + ", Designation=" + Designation + '}';
    }
    
    
    
    
}
