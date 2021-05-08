package Employees.BuisnessLayer;

import Employees.DataAccessLayer.DAOs.EmployeeDAO;
import Employees.DataAccessLayer.DTOs.EmployeeDTO;
import Employees.DataAccessLayer.DTOs.RoleDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Employee {
    private String name;
    private String ID;
    private String bankAccount;
    private int salary;
    private List<Role> roles;
    private TermsOfEmployee terms;
    private LocalDate dateOfHire;
    private EmployeeDTO dto;

    public Employee(String _name, String _ID, LocalDate _dateOfHire) {
        if (!isNameValid(_name))
            throw new IllegalArgumentException("Invalid name");
         if (!isIdValid(_ID))
            throw new IllegalArgumentException("Invalid ID, required 9 digits");
        if (!isDateValid(_dateOfHire))
            throw new IllegalArgumentException("Invalid date");
        name = _name;
        ID = _ID;
        dateOfHire = _dateOfHire;
        roles = new ArrayList<>();
    }

    // copy ctr
    public Employee(Employee other){
        name = other.name;
        ID = other.ID;
        bankAccount = other.bankAccount;
        salary = other.salary;
//        roles = new ArrayList<>();
        roles = other.roles.stream().map(Role::clone).collect(Collectors.toList());
        for (Role role : other.roles){

        }
        terms = new TermsOfEmployee(other.terms);
        dateOfHire = other.dateOfHire;
    }

    public void setDto(EmployeeDAO dao) {
        List<RoleDTO> rolesDTO = new ArrayList<>();
        for (Role role: roles){
            if (role instanceof DriverRole)
                rolesDTO.add(new RoleDTO("Driver", ((DriverRole) role).getLicence()));
            else rolesDTO.add(new RoleDTO(role.getName()));
        }
        dto = new EmployeeDTO(name, ID, bankAccount, salary, getTerms().getSickDays(),
                getTerms().getAdvancedStudyFund(), getTerms().getDaysOff(), dateOfHire, rolesDTO, dao);
        dto.persist();
    }

    private boolean isNameValid(String name){
        return name != null && !name.equals("") && !name.equals(" ");
    }

    private boolean isIdValid(String id){
        return id.length() == 9;
    }

    private boolean isDateValid(LocalDate date){
        LocalDate now = LocalDate.now();
        return true;
    }

    public ResponseT<String> getName() {
        return new ResponseT<String>(name);
    }


    public Response setName(String name) {
        if (!isNameValid(name))
            return new ResponseT("Invalid name");
        this.name = name;
        return new Response();
    }

    public ResponseT<String> getID() {
        return new ResponseT<String>(ID);
    }

    public ResponseT<String> getBankAccount() {
        return new ResponseT<String>(bankAccount);
    }

    public ResponseT<Integer> getSalary() {
        return new ResponseT<Integer>(salary);
    }


    public ResponseT<List<Role>> getRoles() {
        return new ResponseT<>(roles);
    }

    public TermsOfEmployee getTerms() {
        return terms;
    }

    public Response setSalary(int salary) {
        this.salary = salary;
        return new Response();
    }

    public Response setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
        return new Response();
    }

    public Response AddRole(Role role){
        roles.add(role);
        return new Response();
    }

    public Response setTerms(TermsOfEmployee terms) {
        this.terms = terms;
        return new Response();
    }

    public boolean haveRoleCheck(String roleToCheck) {
        for (Role role : roles) {
            if (role.compare(roleToCheck))
                return true;
        }
        return false;
    }

    public String getRolesTostring(){
        StringBuilder res = new StringBuilder("[");
        for(int i = 0 ; i < roles.size() ; i++){
            if(i < roles.size() - 1){
                res.append(roles.get(i).getName()).append(", ");
            }
            else{
                res.append(roles.get(i).getName()).append("]");
            }
        }
        return res.toString();
    }


    //Check if this employee is HR/generarManager authorize
    public ResponseT<Boolean> checkAuthorizedHrOrGenral(){
        if(this.haveRoleCheck("HR Manager") || this.haveRoleCheck("General Manager"))
            return new ResponseT<Boolean>(true);
        return new ResponseT<Boolean>(false);
    }

    public ResponseT<String> getEmpDataTostring(){
        return new ResponseT<String>("Name: %s \nID: %s \nBank Account: %s \nSalary: %s \nRoles: %s \nDate Of Hire: %s".formatted(
                name, ID, bankAccount, salary, getRolesTostring(), dateOfHire)
        );
        //what kind of error Response should it return??
    }



}
