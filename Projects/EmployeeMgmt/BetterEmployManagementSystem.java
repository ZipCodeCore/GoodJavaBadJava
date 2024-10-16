// Level-2


import java.util.*;
import java.io.*;

/************************* Employee Details ************************/

class EmployeeDetail {
  private String name;
  private String email;
  private String position;
  private String employ_id;
  private String employ_salary;
  private String employ_contact;

  // Getters and Setters
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getEmploy_id() {
    return employ_id;
  }

  public void setEmploy_id(String employ_id) {
    this.employ_id = employ_id;
  }

  public String getEmploy_salary() {
    return employ_salary;
  }

  public void setEmploy_salary(String employ_salary) {
    this.employ_salary = employ_salary;
  }

  public String getEmploy_contact() {
    return employ_contact;
  }

  public void setEmploy_contact(String employ_contact) {
    this.employ_contact = employ_contact;
  }

  /* 
   * this is not ideal, as it couples the class to the console
   * it would be better to have a method that takes a Scanner as an argument
   * and reads the data from it
   */
  public static EmployeeDetail captureEmployeeInfo() {
    EmployeeDetail employee = new EmployeeDetail();
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter Employee's name --------: ");
    employee.setName(sc.nextLine());
    System.out.print("Enter Employee's ID ----------: ");
    employee.setEmploy_id(sc.nextLine());
    System.out.print("Enter Employee's Email ID ----: ");
    employee.setEmail(sc.nextLine());
    System.out.print("Enter Employee's Position ----: ");
    employee.setPosition(sc.nextLine());
    System.out.print("Enter Employee contact Info --: ");
    employee.setEmploy_contact(sc.nextLine());
    System.out.print("Enter Employee's Salary ------: ");
    employee.setEmploy_salary(sc.nextLine());
    sc.close();

    return employee;
  }
}

/***************************** Main Class *******************************/
public class BetterEmployManagementSystem {

    Scanner scannerIn = new Scanner(System.in);

  public static void main(String arv[]) {
    BetterEmployManagementSystem ems = new BetterEmployManagementSystem();
    ems.runInteractiveLoop();
  }

  public void runInteractiveLoop() {
    /** To clear the output Screen **/
    System.out.print("\033[H\033[2J");

    int choice = 0;

    this.printMenu();
    /*** Initialising loop for Menu Choices ***/
    while (choice < 6) {

      System.out.print("\nPlease Enter choice :");
      choice = Integer.parseInt(scannerIn.nextLine());

      /** Switch Case Statements **/
      switch (choice) {
        case 1: { // add employee
          this.addEmployee();
          this.printMenu();
          break;
        }
        case 2: {
          String s = promptForEmployeeId();
          try {
            this.viewEmployee(s);
          } catch (Exception e) {
            System.out.println(e);
          }

          continuePrompt();
          this.printMenu();
          break;
        }

        case 3: {
          String s = promptForEmployeeId();
          this.removeEmployee(s);

          continuePrompt();
          this.printMenu();
          break;
        }
        case 4: {
          String s = promptForEmployeeId();
          try {
            this.viewEmployee(s);
          } catch (Exception e) {
            System.out.println(e);
          }
         

          System.out.print("Please Enter the field you want to Update :");
          System.out.print("\nFor Example :\n");
          System.out.println(
              "If you want to Change the Name, then Enter Current Name and Press Enter. \nThen write the new Name then Press Enter. \nIt will Update the Name.\n");
          String currentVal = scannerIn.nextLine();
          System.out.print("Please Enter the Updated Info :");
          String newVal = scannerIn.nextLine();
          try {
            this.updateEmployee(s, currentVal, newVal);

            continuePrompt();
            this.printMenu();
            break;
          } catch (IOException e) {
            System.out.println(e);
          }
        }
        case 5: {
          this.exitSystem();
        }
      }
    }
  }

  private void continuePrompt() {
    System.out.print("\nPress Enter to Continue...");
    scannerIn.nextLine();
  }

  private String promptForEmployeeId() {
    System.out.print("\nPlease Enter Employee's ID :");
    String s = scannerIn.nextLine();
    return s;
  }

  public void addEmployee() {

    EmployDetail emp = new EmployDetail();
    emp.getInfo();
    try {
      File f1 = new File("file" + emp.employ_id + ".txt");
      if (f1.createNewFile()) {
        FileWriter myWriter = new FileWriter("file" + emp.employ_id + ".txt");
        myWriter.write("Employee ID:" + emp.employ_id + "\n" + "Employee Name     :" + emp.name + "\n" +
            "Father's Name     :" + emp.father_name + "\n" + "Employee Contact  :" + emp.employ_contact + "\n" +
            "Email Information :" + emp.email + "\n" + "Employee position :" + emp.position + "\n" +
            "Employee Salary   :" + emp.employ_salary);
        myWriter.close();
        System.out.println("\nEmployee has been Added :)\n");
        continuePrompt();
      } else {
        System.out.println("\nEmployee already exists :(");
        continuePrompt();
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void viewEmployee(String s) throws Exception {
    File file = new File("file" + s + ".txt");
    Scanner sc = new Scanner(file);

    while (sc.hasNextLine()) {
      System.out.println(sc.nextLine());
    }
    sc.close();
  }

  public void removeEmployee(String ID) {

    File file = new File("file" + ID + ".txt");
    if (file.exists()) {
      if (file.delete())
        ;
      {
        System.out.println("\nEmployee has been removed Successfully");
      }
    } else {
      System.out.println("\nEmployee does not exists :( ");
    }
  }

  public void updateEmployee(String s, String o, String n) throws IOException {
    File file = new File("file" + s + ".txt");
    Scanner sc = new Scanner(file);
    String fileContext = "";
    while (sc.hasNextLine()) {
      fileContext = fileContext + "\n" + sc.nextLine();
    }
    fileContext = fileContext.replaceAll(o, n);

    FileWriter myWriter = new FileWriter("file" + s + ".txt");
    myWriter.write(fileContext);
    myWriter.close();

    sc.close();
    /*
     * There is a really insidious bug here. 
     * the replaceAll method is replacing all occurrences of the string,
     * so if the user wants to change the name from "John" to "Johnny",
     * it will also change the name from "Johnson" to "Johnnyson".
     * Among other problems...
     */
  }

  public void printMenu() {
    System.out.print("\033[H\033[2J");

    System.out.println("\t\t*******************************************");
    System.out.println("\t\t\t  EMPLOYEE MANAGEMENT SYSTEM");
    System.out.println("\t\t*******************************************");
    System.out.println("\n\nPress 1 : To Add an Employee Details");
    System.out.println("Press 2 : To See an Employee Details ");
    System.out.println("Press 3 : To Remove an Employee");
    System.out.println("Press 4 : To Update Employee Details");
    System.out.println("Press 5 : To Exit the EMS Portal");

  }

  public void exitSystem() {
    System.out.println("\n*****************************************");
    System.out.println("$ cat Thank You For Using my Software :) ");
    System.out.println("*****************************************");
    System.exit(0);
  }
}
