abstract class LeaveEmployee {
    protected String name;

    public LeaveEmployee(String name) {
        this.name = name;
    }

    public abstract boolean isLeaveAllowed(int days);

    public String getName() {
        return name;
    }
}

class LeaveFullTimeEmployee extends LeaveEmployee {
    public LeaveFullTimeEmployee(String name) {
        super(name);
    }

    public boolean isLeaveAllowed(int days) {
        return days <= 30;
    }
}

class LeavePartTimeEmployee extends LeaveEmployee {
    public LeavePartTimeEmployee(String name) {
        super(name);
    }

    public boolean isLeaveAllowed(int days) {
        return days <= 10;
    }
}

class LeaveContractor extends LeaveEmployee {
    public LeaveContractor(String name) {
        super(name);
    }

    public boolean isLeaveAllowed(int days) {
        return days <= 5;
    }
}

class EmployeeLeaveRequest {
    private LeaveEmployee employee;
    private String startDate;
    private String endDate;
    private int days;
    private String status;

    public EmployeeLeaveRequest(LeaveEmployee employee,
                                String startDate,
                                String endDate,
                                int days) {
        if (!employee.isLeaveAllowed(days))
            throw new IllegalArgumentException("Leave limit exceeded");

        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        status = "Pending";
    }

    public void approve() {
        if (status.equals("Pending"))
            status = "Approved";
    }

    public void reject() {
        if (status.equals("Pending"))
            status = "Rejected";
    }

    public void changeToPending() {
        if (!status.equals("Pending"))
            System.out.println("Cannot change leave request status from "
                    + status + " to Pending.");
    }

    public void display() {
        System.out.println("Leave request submitted for "
                + employee.getName() + " (" + startDate + "-" + endDate + ").");
        System.out.println("Status: " + status);
    }

    public String getStatus() {
        return status;
    }

    public String getEmployeeName() {
        return employee.getName();
    }

    public String getDates() {
        return startDate + "-" + endDate;
    }
}

class LeaveReviewer {
    private String name;

    public LeaveReviewer(String name) {
        this.name = name;
    }

    public void approve(EmployeeLeaveRequest request) {
        request.approve();
        System.out.println(request.getEmployeeName() +
                "'s leave request (" + request.getDates() +
                ") approved. Status: " + request.getStatus());
    }

    public void reject(EmployeeLeaveRequest request) {
        request.reject();
        System.out.println(request.getEmployeeName() +
                "'s leave request (" + request.getDates() +
                ") rejected. Status: " + request.getStatus());
    }
}

public class EmployeeLeaveWorkflow {
}