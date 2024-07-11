package org.controller;

import org.enums.MessageStatus;
import org.enums.ProjectStatus;
import org.enums.Role;
import org.enums.Status;
import org.models.*;
import org.service.ClientService;

import javax.security.auth.Subject;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

public class Console {
    Scanner sc = new Scanner(System.in);
    UserController controller = new UserController();
    ClientController clientController = new ClientController();

    ProjectController projectController = new ProjectController();

    TeamController teamController = new TeamController();

    TaskController taskController=new TaskController();

    TaskUpdateController taskUpdateController=new TaskUpdateController();

    MessageController messageController=new MessageController();

    MilestoneController milestoneController=new MilestoneController();

    public void start() {
        System.out.println("******************************************------------------------------********************************************");
        System.out.println("                                          Welcome to Rev Task Management                                             ");
        System.out.println("******************************************------------------------------**********************************************");
        System.out.println();
        System.out.println("---------Login------------");
        System.out.print("Enter your email address ✉️: ");
        String email = sc.nextLine();
        System.out.println("-------------------------------------------------------------");
        System.out.print("Enter your password 🔐: ");
        String password = sc.nextLine();
        User user=controller.login(email, password);
        if(user==null){
            System.out.println("❌  Incorrect  email   ❌");
            start();
        }
        if(user.getPassword().equals(password)){
            if(user.getStatus()!= Status.OFFLINE) {
                UserController.user = user;
                System.out.println("****************************************************");
                System.out.println("👋 welcome " + user.getName() + " to rev task management");
                if (user.getRole() == Role.ADMIN) {
                    adminOptions();
                } else if (user.getRole() == Role.MANAGER) {
                    System.out.println("👋 welcome " + user.getName() + " to rev task management");
                    managerOptions();
                } else {
                    associateOptions();
                }
            }
            else {
                System.out.println("You can't login as you are deactivated");
                System.exit(1);
            }
        }
        else{
            System.out.println("❌  Password Incorrect  ❌");
            start();
        }
    }

    public void adminOptions() {
        System.out.println("-----------    Welcome Admin    ----------------------");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("1️⃣.create credentials  ||   2️⃣. update employee  ||   3️⃣. Add project   ||   4️⃣. Add client   ||  5️⃣. Deactivate employee   ||  6️⃣. update Project");
        System.out.println("7️⃣. Show messages  8️⃣. Track Activity  9️⃣. update client  🔟. logout");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("Choose your option: ");
        int num = sc.nextInt();
        sc.nextLine();
        switch (num) {
            case 1: {
                createUser();
                break;
            }
            case 2: {
                updateUser();
                adminOptions();
                break;
            }
            case 3: {
                createProject();
                break;
            }
            case 4: {
                addClient();
                break;
            }
            case 5: {
                deleteEmployee();
                break;
            }
            case 6: {
                updateProject();
                break;
            }
            case 7:{
                myMessages(UserController.user.getUser_id());
                adminOptions();
            }
            case 8:{
                trackActivity();
            }
            case 9:{
                updateClient();
            }
            case 10:{
                System.out.println("👋👋Thank you visit again");
                start();
            }
            default: {
                System.out.println("❌ Invalid input. Please try again ❌");
                adminOptions();
            }

        }
    }

    public void createUser() {
        User user = new User();
        System.out.println("-------- creating user --------");
        System.out.print("Enter user name: ");
        user.setName(sc.nextLine());
        System.out.println("--------------------------------");
        System.out.print("Enter your email: ");
        user.setEmail(sc.nextLine());
        System.out.println("--------------------------------");
        System.out.print("Enter mobile number: ");
        user.setMobile(sc.nextLine());
        System.out.println("--------------------------------");
        System.out.print("Enter password: ");
        user.setPassword(sc.nextLine());
        while (true) {
            System.out.println("--------------------------------");
            System.out.print("Select type of member 1️⃣. Manager  2️⃣. Associate ");
            int num = sc.nextInt();
            sc.nextLine();
            if (num == 1 || num == 2) {
                if (num == 1) {
                    user.setRole(Role.MANAGER);
                } else {
                    user.setRole(Role.ASSOCIATE);
                }
                break;
            } else {
                System.out.println("Enter valid input!!!!");
            }
        }
        System.out.println("--------------------------------");
        System.out.print("Enter user description: ");
        user.setDescription(sc.nextLine());
        user.setStatus(Status.ONLINE);
        boolean b=controller.addUser(user);
        if(b){
            System.out.println("Registration successfull");
            adminOptions();
        }
        else{
            System.out.println("User with that mail already exist");
            adminOptions();
        }
    }

    public void addClient() {
        Client client = new Client();
        System.out.println("---------  Creating Client   ----------");
        System.out.print("Enter company name: ");
        client.setCompany_name(sc.nextLine());
        System.out.println("----------------------------------------");
        System.out.print("Enter name of point of contact:");
        client.setPoint_of_contact(sc.nextLine());
        System.out.println("-----------------------------------------");
        System.out.print("Enter contact email: ");
        client.setContact_email(sc.nextLine());
        System.out.println("-----------------------------------------");
        System.out.print("Enter contact number: ");
        client.setContact_number(sc.nextLine());
        System.out.println("-----------------------------------------");
        System.out.print("Enter company city: ");
        client.setCity(sc.nextLine());
        System.out.println("------------------------------------------");
        System.out.print("Enter company address: ");
        client.setAddress(sc.nextLine());
        clientController.addClient(client);

    }

    public void createProject() {
        System.out.println("------------- Creating project -------------");
        Project project = new Project();
        System.out.println("Select the client: ");
        List<Client> clients = clientController.getAllClients();
        for (Client c : clients) {
            System.out.println("   ||   " + (c.getClient_id()) + ". " + c.getCompany_name());
            System.out.println("-----------------------------------------------------");
        }
        System.out.print("Choose client: ");
        int num = sc.nextInt();
        sc.nextLine();
        if (num > 0 && num <= clients.size()) {
            project.setClient(clients.get(num - 1));
        } else {
            createProject();
        }
        System.out.println("-----------------------------------");
        System.out.print("Enter project name: ");
        project.setProject_name(sc.nextLine());
        System.out.println("----------------------------------");
        System.out.print("Enter project description: ");
        project.setDescription(sc.nextLine());
        System.out.println("-----------------------------------");
        System.out.println("🔍🔍Choose manager for the project");
        List<User> managers = controller.getAllManagers();
        for (int i = 0; i < managers.size(); i++) {
            System.out.println("   ||   " + (i + 1) + ". " + managers.get(i).getName());
            System.out.println("----------------------------------------------------------");
        }
        System.out.print("Choose option: ");
        int val = sc.nextInt();
        sc.nextLine();
        if (val > 0 && val <= managers.size()) {
            project.setManager(managers.get(val - 1));
        } else {
            createProject();
        }
        Project project1=projectController.addProject(project);
        if(project1==null){
            System.out.println("something went wrong");
            adminOptions();
        }
        else {
            Project project2=projectController.getProjectByName(project1.getProject_name());
            project1.setProject_id(project2.getProject_id());
            createTeam(project1);
        }

    }
    public void deleteEmployee(){
        System.out.println("-----------------------------------------------------------");
        List<User> users = controller.getAllUsers().stream()
                .filter(user -> user.getStatus()==Status.ONLINE)
                .toList();

        for(int i=1;i<users.size();i++) {
            System.out.println(i + ". [name: " + users.get(i).getName() + " ]     [Designation: " + users.get(i).getRole() + "]");
        }
        System.out.println("0. Go to previous page");
            System.out.println("Choose whom you want to deactivate: ");

            int num=sc.nextInt();
            sc.nextLine();
            if(num>=0 && num<users.size()){
                if(num==0){
                    adminOptions();
                }
                else{
                    User update=users.get(num);
                    update.setStatus(Status.OFFLINE);
                    controller.updateUser(update);
                    System.out.println("Update Successfully");
                    adminOptions();
                }
                User manager=controller.getUserById(UserController.user.getManager());
                String subject="Deactivating user "+UserController.user.getName();
                String message="Update task of user I am deactivating"+UserController.user.getName();
                sendSetOfMessages(UserController.user,manager,subject,message);
            }
            else{
                System.out.println("Enter valid input");
                deleteEmployee();
            }


    }

    public void createTeam(Project project) {
        System.out.println("🟢🟢Lets create a team for the project🟢🟢");
        Team team = new Team();
        team.setManager(project.getManager());
        team.setProject(project);
        System.out.print("Enter team name: ");
        team.setTeam_name(sc.nextLine());
        teamController.createTeam(team);

    }

    public void updateUser() {
        System.out.println("Choose the employee you want to update: ");
        List<User> users = controller.getAllUsers();
        for (int i = 1; i < users.size(); i++) {
            if (i % 2 != 0) {
                System.out.println();
            }
            System.out.print(" ➡️  " + i + ". " + users.get(i).getName() + "   🚦🚦 ");
        }
        System.out.print("Enter the option: ");
        int id = sc.nextInt();
        User updateUser = users.get(id);
        sc.nextLine();
        if (id > 0 && id <= users.size()) {
            updateUser.setUser_id(users.get(id).getUser_id());
            System.out.println("Choose what you want to update: ");
            while (true) {
                System.out.println("If you want feel you have updated the required fields press 0");
                System.out.println(" 1️⃣. name 🚦🚦2️⃣. email 🚦🚦3️⃣. mobile 🚦🚦4️⃣. make him manager 🚦🚦5️⃣. Description");
                int num = sc.nextInt();
                sc.nextLine();
                switch (num) {
                    case 1: {
                        System.out.print("Enter new name: ");
                        System.out.println("---------------------------------------");
                        updateUser.setName(sc.nextLine());
                        break;
                    }
                    case 2: {
                        System.out.print("Enter new email: ");
                        updateUser.setEmail(sc.nextLine());
                        System.out.println("---------------------------------------");
                        break;
                    }
                    case 3: {
                        System.out.print("Enter new mobile number: ");
                        updateUser.setMobile(sc.nextLine());
                        System.out.println("---------------------------------------");
                        break;
                    }
                    case 4: {
                        if (users.get(id).getRole() == Role.MANAGER) {
                            System.out.print("He is already manager: ");

                        } else {
                            updateUser.setRole(Role.MANAGER);
                        }
                        break;
                    }
                    case 5: {
                        System.out.print("Enter new description: ");
                        updateUser.setDescription(sc.nextLine());
                        System.out.println("---------------------------------------");
                        break;

                    }
                }
                if (num == 0) {
                    controller.updateUser(updateUser);
                    break;
                }

            }
        } else {
            System.out.println("Enter valid input: ");
            updateUser();
        }

    }

    public void updateProject(){
        System.out.println("----------------------------------------------------------------");
        List<Project> projects=projectController.fetchAllProjects();
        for(int i=0;i<projects.size();i++){
            System.out.println((i+1)+". [Project name: "+projects.get(i).getProject_name()+"]    [Status: "+projects.get(i).getStatus()+"]");
        }
        System.out.println("0. Go to previous page");
        System.out.println("Choose your option: ");
        int num=sc.nextInt();
        sc.nextLine();
        if(num>=0 && num<=projects.size()){
            if(num==0){
                adminOptions();
            }
            else{
                Project project=projects.get(num-1);
                while (true){
                    System.out.println("Enter 0 if you feel you have updated all the fields");
                    System.out.println("1️⃣. Update end date      2️⃣. update Status");
                    System.out.println("3️⃣. change manager       0️⃣.exit");
                    System.out.println("Choose your option: ");
                    int val= sc.nextInt();;
                    sc.nextLine();
                    if(val>0 && val<4){
                        switch (val){
                            case 1:{
                                System.out.print("Enter end date(year-mon-date): ");
                                String date=sc.nextLine();
                                project.setEnd_date(Date.valueOf(date));
                                break;
                            }
                            case 2:{
                                ProjectStatus ps=project.getStatus();
                                if(ps==ProjectStatus.PROGRESS){
                                    project.setStatus(ProjectStatus.FINISHED);
                                    System.out.println("Project set to finished");
                                }
                                else {
                                    project.setStatus(ProjectStatus.PROGRESS);
                                    System.out.println("Project set back to progress");
                                }
                                break;
                            }
                            case 3:{
                                List<User> managers = controller.getAllManagers();
                                for (int i = 0; i < managers.size(); i++) {
                                    System.out.println("   ||   " + (i + 1) + ". " + managers.get(i).getName());
                                    System.out.println("----------------------------------------------------------");
                                }
                                System.out.print("Choose option: ");
                                int man = sc.nextInt();
                                sc.nextLine();
                                if (man > 0 && man <= managers.size()) {
                                    project.setManager(managers.get(man - 1));
                                } else {
                                    System.out.println("Enter valid input");
                                    updateProject();
                                }
                                break;
                            }

                        }
                    }
                    else if(val==0){
                        projectController.updateProject(project);
                        System.out.println("update successful");
                        break;
                    }
                    else{
                        System.out.println("Enter valid options");
                        updateProject();
                    }


                }
                adminOptions();
            }
        }
        else {
            System.out.println("Enter valid options");
            updateProject();
        }
            }
    public void trackActivity() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Choose which project you want to know about");
        List<Project> projects = projectController.fetchAllProjects();
        for (int i = 0; i < projects.size(); i++) {
            System.out.println((i + 1) + ". [Project name: " + projects.get(i).getProject_name() + "]    [Status: " + projects.get(i).getStatus() + "]");
        }
        System.out.println("0. Go to previous page");
        System.out.println("Choose your option: ");
        int num = sc.nextInt();
        sc.nextLine();
        if(num>=0 && num<=projects.size()){
            if(num==0){
                adminOptions();
            }
            else{
                Project selectedProject=projects.get(num-1);
                List<Task> tasks=taskController.getProjectTasks(selectedProject.getProject_id());
                int tasks_finished=0,task_left=0;
                if(tasks.isEmpty()){
                    tasks_finished=0;
                    task_left=0;
                }
                else{
                    for(Task task :tasks){
                        if(Objects.equals(task.getStatus(), "ACTIVE")){
                            task_left+=1;
                        }
                        else {
                            tasks_finished+=1;
                        }
                    }
                }
                List<User> users=projectController.projectMembers(selectedProject.getProject_id());
                System.out.println("--------------------------------------------------------");
                System.out.println("[Project name: "+selectedProject.getProject_name()+"]");
                System.out.println("[Manager name: "+selectedProject.getManager().getName()+"]");
                System.out.println("[Project Status: "+selectedProject.getStatus()+"]");
//                System.out.println("");
                System.out.println("[No of users working: "+users.size()+"]");
                System.out.println("[Total tasks created: "+tasks.size()+"]  [Finished tasks: "+tasks_finished+"]  [pending tasks: "+task_left+"]");
                trackActivity();
            }
        }
        else {
            System.out.println("Enter valid options!!!!");
            trackActivity();
        }
    }

    public void updateClient(){
        List<Client> seeClients=clientController.getAllClients();
        System.out.println("---------------------------------------------------------------------");
        for(int i=0;i<seeClients.size();i++){
            System.out.println((i+1)+". [Client name: "+seeClients.get(i).getCompany_name()+"]");
        }
        System.out.println("0. GO to previous page");
        System.out.println("Choose the client you want to update:");
        int num=sc.nextInt();
        sc.nextLine();
        Client client=seeClients.get(num-1);
        if(num>=0 && num<=seeClients.size()){
            if(num==0){
                adminOptions();
            }
            else {
                while (true){
                    System.out.println("-----------------------------------");
                    System.out.println("Choose what you want to update ");
                    System.out.println("1️⃣. to update point of contact");
                    System.out.println("2️⃣. update email     3️⃣. update mobile");
                    System.out.println("4️⃣. stop updating");
                    System.out.println("Choose your option: ");
                    int val=sc.nextInt();
                    sc.nextLine();
                    if(val>0 && val<4){
                        switch (val){
                            case 1:{
                                System.out.println("Enter name of POC:");
                                client.setPoint_of_contact(sc.nextLine());
                                break;
                            }
                            case 2:{
                                System.out.println("Enter new email:");
                                client.setContact_email(sc.nextLine());
                                break;
                            }
                            case 3:{
                                System.out.println("Enter new mobile");
                                client.setContact_number(sc.nextLine());
                                break;
                            }

                        }

                    }
                    else if(val==4){
                        clientController.updateClient(client);
                        System.out.println("updated successfully");
                        adminOptions();
                    }
                    else{
                        System.out.println("Enter valid output");
                        updateClient();
                    }
                }
            }
        }
        else{
            System.out.println("Enter vaild options!!");
            updateClient();
        }


    }
//==================================================================================================
    public void managerOptions() {
        System.out.println("🟢✅ Welcome Manager ✅🟢 ");
        System.out.println("1️⃣. Show my projects");
        System.out.println("2️⃣. My Messages");
        System.out.println("3️⃣. logout");
        System.out.println("Choose your options: ");
        int num = sc.nextInt();
        sc.nextLine();
        if (num == 1) {
            managerProjects();
        } else if (num == 2) {
            myMessages(UserController.user.getUser_id());
            managerProjects();
        }
        else if(num==3){
            start();
        }
        else {
            System.out.println("Invalid option!!!");
            managerOptions();
        }

    }

    public void managerProjects() {
        List<Project> projects = new ArrayList<>();
        projects = projectController.getManagersProject(UserController.user.getUser_id());
        if (projects.isEmpty()) {
            System.out.println("No projects are assigned to you!!!");
        } else {
            System.out.println("*******************************************************************");
            System.out.println("**   Id   **            Name                **    Status     **");
            for (int i = 0; i < projects.size(); i++) {
                System.out.println("**   " + (i + 1) + "  **  " + projects.get(i).getProject_name() + "    **   " + projects.get(i).getStatus() + "   **");
            }
            System.out.println("*******************************************************************");
            System.out.println(" 0. go to back previous page: ");
            System.out.print("Choose your option: ");
            int num = sc.nextInt();
            sc.nextLine();
            if (num <= projects.size() && num >= 0) {
                if (num == 0) {
                    managerOptions();
                } else {
                    Project currentProject = projects.get(num - 1);
                    projectDetails(currentProject);
                }
            }
        }
    }

    public void projectDetails(Project currentProject) {
        System.out.println("******  Project  Details   *******");
        System.out.println("➡️. Project name: " + currentProject.getProject_name());
        System.out.println("➡️. Project details: " + currentProject.getDescription());
        if (currentProject.getEnd_date() != null) {
            System.out.println("➡️. End date: " + currentProject.getEnd_date());
            Instant now = Instant.now();
            Instant futureInstant = currentProject.getEnd_date().toInstant();
            Duration timeLeft = Duration.between(now, futureInstant);
            System.out.println("➡️. Days left: " + timeLeft.toDays() + " days.");
        }
        System.out.println("**************************************");
        System.out.println("⭐⭐ your options: ⭐⭐");
        System.out.println("1️⃣. Open project team         2️⃣. Show client details");
        System.out.println("3️⃣.Go to previous page");
        System.out.print("Enter your options: ");
        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1: {
                teamDetails(currentProject);
            }
            case 2: {
                System.out.println("**************************************************");
                System.out.println("      Client name: "+currentProject.getClient().getCompany_name()+".");
                System.out.println("      Client POC : "+currentProject.getClient().getPoint_of_contact()+".");
                System.out.println("      Client email: "+currentProject.getClient().getContact_email()+".");
                System.out.println("      Client contact: "+currentProject.getClient().getContact_number()+".");
                projectDetails(currentProject);
            }
            case 3: {
                managerOptions();
            }
            default:{
                System.out.println("Enter valid options");
                projectDetails(currentProject);
            }
        }
    }


    public void teamDetails(Project project) {
        Team team = teamController.getTeamByProject(project.getProject_id());
        System.out.println("****************************************************");
        System.out.println("⭐⭐ Team Details  ⭐⭐");
        System.out.println("Team name: " + team.getTeam_name());
        System.out.println("Team members: ");
        List<User> team_members = teamController.getAllTeamMembers(team.getTeam_id());
        if (team_members == null) {
            System.out.println("There are no team members in your team");
        } else {
            for (int i = 0; i < team_members.size(); i++) {
                System.out.println((i + 1) + ". " + "  name: " + team_members.get(i).getName() + "  Description:[" + team_members.get(i).getDescription());
            }
        }
        System.out.println("*************************************************************");
        System.out.println("⭐⭐ Your options  ⭐⭐");
        System.out.println("1️⃣. Add associate to team       2️⃣. Remove associate from team");
        System.out.println("3️⃣. show tasks                  4️⃣. Create a task");
        System.out.println("5️⃣. Update task                 6️⃣. Message to team");
        System.out.println("7️⃣. go to previous page");
        System.out.print("Choose your option: ");
        int num = sc.nextInt();
        sc.nextLine();
        switch (num) {
            case 1: {
                addMember(team, project);
                break;
            }
            case 2: {
                System.out.println("Whom you want to remove:");
                List<User> teamMembers=projectController.projectMembers(project.getProject_id());
                for(int i=0;i<teamMembers.size();i++){
                    System.out.println((i+1)+". [ name: "+teamMembers.get(i).getName()+" ]    [description: "+teamMembers.get(i).getDescription()+"]");
                }
                System.out.print("Choose your options: ");
                int user_id=sc.nextInt();
                sc.nextLine();
                if(user_id>0 && user_id<=teamMembers.size()){
                    User user=teamMembers.get(user_id-1);
                    List<Task> tasks=taskController.userTasks(project.getProject_id(),user.getUser_id());
                    System.out.println("---------------------------------------------------------------------");
                    System.out.println("To whom you want to re assign his tasks");
                    for(int i=0;i<teamMembers.size();i++){
                        System.out.println((i+1)+". [ name: "+teamMembers.get(i).getName()+" ]    [description: "+teamMembers.get(i).getDescription()+"]");
                    }
                    System.out.print("Choose your options: ");
                    int take=sc.nextInt();
                    sc.nextLine();
                    if(user_id>0 && user_id<=teamMembers.size()){
                        User transfer =teamMembers.get(take-1);
                        for(int i=0;i<tasks.size();i++){
                            System.out.println("Into loop");
                            tasks.get(i).setUser(transfer);
                            taskController.updateTask(tasks.get(i));
                            System.out.println("Reassign successfull");
                        }

                        user.setManager(0);
                        System.out.println(user.getManager());
                        controller.updateUser(user);
                        projectController.deleteUserFromProject(user.getUser_id(),project.getProject_id());
                        teamController.removeFromTeam(user);
                        System.out.println("User has been removed!!!!");
                        teamDetails(project);

                    }
                    else {
                        System.out.println("Enter valid options: ");
                        projectDetails(project);
                    }

                }
                else {
                    System.out.println("Enter valid options: ");
                    projectDetails(project);
                }

                break;
            }
            case 3: {
                List<Task> tasks=taskController.getProjectTasks(project.getProject_id());

                System.out.println("-------------------------------------------------------");
                for(int i=0;i<tasks.size();i++){
                    System.out.println((i+1)+". [task name: "+tasks.get(i).getTask_name()+"]" +
                            "   [description: "+tasks.get(i).getDescription()+"]  " +
                            "  [associate assigned: "+tasks.get(i).getUser().getName()+"]" +
                            "  [milestone: "+tasks.get(i).getMilestone().getMilestone_name()+"]");
                }
                System.out.println("---------------------------------------------------------");

                if(tasks.isEmpty()){
                    System.out.println("No tasks created!!!!");
                }
                teamDetails(project);


            }
            case 4: {
                createTask(project);
                teamDetails(project);
            }
            case 5: {
                updateTask(project);
                teamDetails(project);
            }
            case 6:{
                System.out.println("**************************************");
                System.out.println("Enter subject:  ");
                String subject=sc.nextLine();
                System.out.println("Enter context");
                String context=sc.nextLine();
                List<User> users=projectController.projectMembers(project.getProject_id());
                for(User user:users){
                    sendSetOfMessages(project.getManager(),user,subject,context);
                }
                System.out.println("Message sent successfully");
            }
            case 7:{
                managerOptions();
            }
            default:{
                System.out.println("Enter valid input");
                managerOptions();
            }

        }
    }

    public void addMember(Team team, Project project) {
        System.out.println("*****************************************************************");
        List<User> associates = new ArrayList<>(controller.getAllUsers().stream().filter(user -> {
            return user.getManager() == 0 && user.getStatus()==Status.ONLINE;
        }).filter(user -> {
            return user.getRole() == Role.ASSOCIATE;
        }).toList());
        if (associates.isEmpty()) {
            System.out.println("No team  members !!!");
        } else {
            List<User> teamList = new ArrayList<>();
            while (true) {
                for (int i = 0; i < associates.size(); i++) {
                    System.out.println("---------------------------------------------------------");
                    System.out.println(+(i + 1) + ". [name: " + associates.get(i).getName() + "]   [description: " + associates.get(i).getDescription() + " ]");
                    System.out.println(associates.get(i).getManager());
                }
                System.out.println("You want to exit enter 0");
                System.out.print("Choose your option: ");
                int num = sc.nextInt();
                sc.nextLine();
                if (num >= 0 && num <= associates.size()) {
                    if (num == 0) {
                        break;
                    } else {
                        teamList.add(associates.get(num - 1));
                        associates.remove(num - 1);
                    }
                } else {
                    System.out.println("Enter valid options");
                }
            }
            for (User member : teamList) {
                boolean b = teamController.addTeamMembersToProject(team, member, project);
                if (!b) {
                    System.out.println("Something went wrong");
                    teamDetails(project);
                }
            }
            System.out.println("🟢✅   Members has been added successfully!!!  ✅🟢");

        }
        teamDetails(project);
    }

    public void createTask(Project project){
        List<User> teamMembers=projectController.projectMembers(project.getProject_id());
//        System.out.println(teamMembers);
        Task task=new Task();
        System.out.println("⭐⭐⭐ Lets create a Task ⭐⭐⭐");
        System.out.print("Enter task name: ");
        task.setTask_name(sc.nextLine());
        System.out.println("**********************************************");
        System.out.print("Enter task description: ");
        task.setDescription(sc.nextLine());
        System.out.print("Enter end date(year-mon-date): ");
        String date=sc.nextLine();
        System.out.println("Choose to whom you want to assign: ");
        for(int i=0;i<teamMembers.size();i++){
            System.out.println((i+1)+". [ name: "+teamMembers.get(i).getName()+" ]    [description: "+teamMembers.get(i).getDescription()+"]");
        }
        System.out.print("Choose your options: ");
        int user_id=sc.nextInt();
        sc.nextLine();
        if(user_id>0 && user_id<=teamMembers.size()){
            task.setUser(teamMembers.get(user_id-1));
        }
        else {
            System.out.println("Enter valid options: ");
            createTask(project);
        }
//        System.out.print("Enter your comments on task: ");
        task.setProject(project);
        task.setStart_date(Date.valueOf(LocalDate.now()));
//        task.setMilestone();
        Milestone milestone=new Milestone();
        milestone.setMilestone_id(1);
        task.setStatus("ACTIVE");
        task.setMilestone(milestone);
        task.setEnd_date(Date.valueOf(date));

        Task dbtask=taskController.createTask(task);
        TaskUpdate taskUpdate=new TaskUpdate();
        taskUpdate.setTask(dbtask);
        taskUpdate.setMilestone(dbtask.getMilestone());
        taskUpdate.setUser(dbtask.getUser());
        taskUpdate.setTimestamp(Timestamp.from(Instant.now()));
        taskUpdate.setComments("Task has been created and assigned to "+dbtask.getUser().getName());
        taskUpdateController.createTimeLine(taskUpdate);

        teamDetails(project);
    }

    public void updateTask(Project project){
        List<Task> tasks=taskController.getProjectTasks(project.getProject_id());
        System.out.println("-----------------------------------------------------");
        for(int i=0;i<tasks.size();i++){
            System.out.println((i+1)+". [task name: "+tasks.get(i).getTask_name()+"]" +
                    "   [description: "+tasks.get(i).getDescription()+"]  " +
                    "  [associate assigned: "+tasks.get(i).getUser().getName()+"]" +
                    "  [milestone: "+tasks.get(i).getMilestone().getMilestone_name()+"]");
        }
        System.out.println("0. to go back to previous page");
        System.out.println("Choose your option:");
        int num=sc.nextInt();
        sc.nextLine();
        if(num>=0 && num<=tasks.size()) {
            if (num == 0) {
                teamDetails(project);
            } else {
                Task update_task = tasks.get(num - 1);
                System.out.println("--------------------------------------------------");
                System.out.println("1️⃣. update mileStone        2️⃣. assign to another");
                System.out.println("3️⃣. change status           4️⃣. Go to previous page");
                System.out.println("Choose your option: ");
                int option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case 1: {
                        System.out.println("Choose among");
                        System.out.println("1️⃣. In queue  2️⃣.Commenced  3️⃣. Implemented");
                        System.out.println("4️⃣. Testing   5️⃣. Test passed  6️⃣. Code review");
                        System.out.println("7️⃣. Approval");
                        System.out.print("Enter your option: ");
                        int milestone = sc.nextInt();
                        sc.nextLine();
                        if (milestone > 0 && milestone < 8) {
                            update_task.setMilestone(milestoneController.getMilestoneById(milestone));
                        } else {
                            System.out.println("Enter valid options");
                            updateTask(project);
                        }
                        break;
                    }
                    case 2: {
                        List<User> teamMembers=projectController.projectMembers(project.getProject_id());
                        System.out.println("Choose to whom you want to assign: ");
                        for(int i=0;i<teamMembers.size();i++){
                            System.out.println((i+1)+". [ name: "+teamMembers.get(i).getName()+" ]    [description: "+teamMembers.get(i).getDescription()+"]");
                        }
                        System.out.print("Choose your options: ");
                        int user_id=sc.nextInt();
                        sc.nextLine();
                        if(user_id>0 && user_id<=teamMembers.size()){
                            update_task.setUser(teamMembers.get(user_id-1));
                        }
                        else {
                            System.out.println("Enter valid options: ");
                            createTask(project);
                        }
                        break;

                    }
                    case 3: {
                            if(Objects.equals(update_task.getStatus(), "ACTIVE")){
                                update_task.setStatus("CLOSED");
                                System.out.println("Task has been closed");
                            }
                            else {
                                update_task.setStatus("ACTIVE");
                                System.out.println("Task has been reopened");
                            }
                            break;
                    }
                    case 4: {
                            teamDetails(project);
                            break;
                    }
                    default: {
                        System.out.println("Enter valid input");
                        updateTask(project);
                    }
                }
                taskController.updateTask(update_task);
                System.out.println("Updated successfully");

            }

        }

         else {
            System.out.println("Enter valid input");
            updateTask(project);
        }

    }

//    ===========================================================================================

    public void associateOptions(){
        System.out.println("********************************************");
        System.out.println("Welcome associate: ");
        System.out.println("1️⃣. Show my projects      2️⃣. Show my messages");
        System.out.println("3️⃣. logout");
        System.out.print("Choose your option: ");
        int output=sc.nextInt();
        sc.nextLine();
        switch (output){
            case 1:{
                showProjects();
            }
            case 2:{
                myMessages(UserController.user.getUser_id());
                associateOptions();
            }
            case 3:{
                start();
                break;
            }
            default:{
                System.out.println("Enter valid input!!!!");
                associateOptions();
            }
        }
    }
    public void showProjects(){
        User user=UserController.user;
        List<Project> projects=projectController.projectsOfMembers(user.getUser_id());
        if(projects.isEmpty()){
            System.out.println("There are no projects assigned to you!!");
            associateOptions();
        }
        else{
//            System.out.println(projects);
            System.out.println("------------   List of your projects   -------------");
            for(int i=0;i<projects.size();i++){
                System.out.println((i+1)+". [name: "+projects.get(i).getProject_name()+" ]  [status: "+projects.get(i).getStatus()+" ]");
            }
            System.out.println("0. to go back to previous page");
            System.out.print("Choose your option: ");
            int input=sc.nextInt();
            sc.nextLine();
            if(input>=0 && input<=projects.size()){
                if(input==0){
                    associateOptions();
                }
                else{
                    associateProject(projects.get(input-1));
                }
            }
            else {
                System.out.println("Enter valid input!!!!!!");
                showProjects();
            }
        }
    }

    public void associateProject(Project project){
        System.out.println("--------     These are your project details:   --------");
        System.out.println("[ name: "+project.getProject_name()+" ] | [ project description: "+
                project.getDescription()+"]" );
        System.out.println("*****    Choose your options:    *****");
        System.out.println("1️⃣. Show my tasks ");
        System.out.println("2️⃣. Go to previous page");
        System.out.println("Enter input: ");
        int input= sc.nextInt();sc.nextLine();
        switch (input){
            case 1:{
                userTasks(project);
            }
            case 2:{
                showProjects();
            }
            default: {
                System.out.println("Enter valid options: ");
                associateProject(project);

            }
        }

    }

    public void userTasks(Project project){
        List<Task> myTasks=taskController.userTasks(project.getProject_id(),UserController.user.getUser_id());
        if(myTasks.isEmpty()){
            System.out.println("🔴🔴🔴 No tasks assigned to you  🔴🔴🔴");
            associateProject(project);
        }
        else{
            for(int i=0;i<myTasks.size();i++){
                System.out.println((i+1)+". [name: "+myTasks.get(i).getTask_name()+" ] [Status: "+myTasks.get(i).getStatus()+" ]");
            }
            System.out.println("0. go to previous page");
            System.out.print("Choose your task: ");
            int num= sc.nextInt();
            sc.nextLine();
            if(num>=0 && num<=myTasks.size()){
                if(num==0){
                    associateProject(project);
                }
                else{
                    associateTaskOptions(myTasks.get(num-1));
                }

            }
            else{
                System.out.println("🔴🔴 Enter valid input 🔴🔴");
                userTasks(project);
            }
        }
    }
    public void associateTaskOptions(Task task){
        List<TaskUpdate> updates=taskUpdateController.taskUpdatesOfTask(task.getTask_id());
        System.out.println("--------------------------------------------------");
        for(int i=0;i<updates.size();i++){
            System.out.println(i+". [ "+updates.get(i).getMilestone().getMilestone_name()+"]  |   " +
                    "[ "+updates.get(i).getComments()+" ]");
        }
        System.out.println("------------------------------------------------------");
        System.out.println("What you want to do: ");
        System.out.println("1️⃣. Show task description and details");
        System.out.println("2️⃣. update the task");
        System.out.println("3️⃣. Connect with team");
        System.out.println("4️⃣. Message to manager");
        System.out.println("5️⃣. go to previous page");
        System.out.print("Choose your option: ");
        int input=sc.nextInt();
        sc.nextLine();
       switch (input){
           case 1:{
               System.out.println("-----------------------------------------------------------");
               System.out.println("Task name: "+task.getTask_name());
               System.out.println("Task description: "+task.getTask_name());
               System.out.println("Task start date: "+task.getStart_date());
               System.out.println("Task end date: "+task.getEnd_date());
               System.out.println("Task manager name: "+task.getProject().getManager().getName());
               System.out.println("Present milestone: "+task.getMilestone().getMilestone_name());
               System.out.println("----------------------------------------------------------");
               associateTaskOptions(task);
           }
           case 2:{
               if(task.getMilestone().getMilestone_id()>=6){
                   System.out.println("----------------------------");
                   System.out.println("You can't update!!!!!");
                   associateTaskupdate(task);
               }
               else {
                   associateTaskupdate(task);
               }

           }
           case 3:{
                connectWithTeam(task);
           }
           case 4:{
                generateMessages(UserController.user,task.getProject().getManager());
                associateTaskOptions(task);
           }
           case 5:{
               userTasks(task.getProject());

           }
           default:{
               System.out.println("Enter valid input");
               associateTaskOptions(task);
           }

       }


    }

    public void associateTaskupdate(Task task){
        System.out.println("Current state of task is :" +task.getMilestone().getMilestone_name());
        int num=task.getMilestone().getMilestone_id();
        if(num>1){
            Milestone previous=milestoneController.getMilestoneById(num-1);
            Milestone next=milestoneController.getMilestoneById(num+1);
            System.out.println("What to want to do: ");
            System.out.println("1. [milestone: "+previous.getMilestone_name()+"]");
            System.out.println("2. [milestone: "+next.getMilestone_name()+"]");
            System.out.print("Enter your options: ");
            int val=sc.nextInt();sc.nextLine();
            if(val==1 || val==2){
                if(val==1){
                    task.setMilestone(previous);
                    taskController.updateTask(task);
                    taskUpdate(task);
                    System.out.println("Timeline has been updated!!!!");
                    System.out.println("-----------------------------------");
                    associateTaskOptions(task);
                }
                else{
                    task.setMilestone(next);
                    taskController.updateTask(task);
                    taskUpdate(task);
                    System.out.println("Timeline has been updated!!!!");
                    System.out.println("-----------------------------------");
                    associateTaskOptions(task);
                }
            }
            else {
                System.out.println("Enter valid options");
                associateTaskupdate(task);
            }

        }
        else if(num==1){
            Milestone next=milestoneController.getMilestoneById(num+1);
            System.out.println("1. [milestone: "+next.getMilestone_name()+"]");
            System.out.print("Enter your options: ");
            int val=sc.nextInt();sc.nextLine();
            if(val==1){
                task.setMilestone(next);
                taskController.updateTask(task);
                taskUpdate(task);
                System.out.println("Timeline has been updated!!!!");
                System.out.println("-----------------------------------");
                associateTaskOptions(task);
            }
            else {
                System.out.println("Enter valid options");
                associateTaskupdate(task);
            }
        }
    }

    public void connectWithTeam(Task task){
            List<User> users=projectController.projectMembers(task.getProject().getProject_id());
            int count=1;
            for(User user:users){
                if(user.getUser_id()!=task.getUser().getUser_id()){
                    System.out.println((count++)+". [name: "+user.getName()+"]  " +
                            "[description:  "+user.getDescription());
                }
            }
        System.out.println("0. to go back to previous message");
        System.out.print("Choose your option: ");
        int num=sc.nextInt();
        sc.nextLine();
        if(num>=0 && num<=count){
            if(num==0){
                associateTaskOptions(task);
            }
            else{
                generateMessages(task.getUser(),users.get(num-1));
                associateTaskOptions(task);
            }
        }
        else {
            System.out.println("Enter valid options");
            associateTaskOptions(task);
        }
    }


//    =========================================================================================
    public void generateMessages(User sender,User receiver){
        System.out.println("-----------------------------------------------------------");
        Message message=new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setStatus(MessageStatus.UNSEEN);
        System.out.println("Enter your subject: ");
        String subject=sc.nextLine();
        System.out.println("----------------------------");
        System.out.println("Enter your message: ");
        String context=sc.nextLine();
        message.setSubject(subject);
        message.setContent(context);
        System.out.println("Message sent to :"+message.getReceiver().getName());
        int num=messageController.sendMessage(message);
        if(num>0){
            System.out.println("Message sent succesfully✅✅");
        }

    }
    public void sendSetOfMessages(User sender,User receiver,String subject,String context){
        {
            System.out.println("-----------------------------------------------------------");
            Message message=new Message();
            message.setSender(sender);
            message.setReceiver(receiver);
            message.setStatus(MessageStatus.UNSEEN);
            message.setSubject(subject);
            message.setContent(context);
//            System.out.println("Message sent to :"+message.getReceiver().getName());
            int num=messageController.sendMessage(message);
            if(num>0){
//                System.out.println("Message sent succesfully✅✅");
            }

        }
    }

    public void taskUpdate(Task task){
        TaskUpdate taskUpdate=new TaskUpdate();
        System.out.println("Enter your comments on change milestone");
        taskUpdate.setComments(sc.nextLine());
        taskUpdate.setMilestone(task.getMilestone());
        taskUpdate.setUser(task.getUser());
        taskUpdate.setTask(task);
        taskUpdate.setTimestamp(Timestamp.from(Instant.now()));
        taskUpdateController.createTimeLine(taskUpdate);

    }

    public void myMessages(int user_id){
        System.out.println("-----------------------------------------------------------------");
       List<Message> messages= messageController.myMessages(user_id);
       if (messages.isEmpty()){
           System.out.println("No messages!!");
           return;
       }

       List<Message> unseen=new ArrayList<>();
       List<Message> seen=new ArrayList<>();
       for(Message message:messages){
           if(message.getStatus()==MessageStatus.UNSEEN){
               unseen.add(message);
           }
           else {
               seen.add(message);
           }
       }
        System.out.println("Choose your option: ");
        if(unseen.size()>0){
            System.out.println("1️⃣. New messages("+unseen.size()+")");
            System.out.println("2️⃣. View my inbox");
            System.out.println("3️⃣. GO to previous page");
            System.out.print("Choose your option: ");
            int num=sc.nextInt();
            sc.nextLine();
            if(num>=1 && num<=3){
                if(num==1){
//                    System.out.println("Into unseen");
                    for (int i=0;i<unseen.size();i++){
                            System.out.println((i+1)+". [from: "+unseen.get(i).getSender().getName()+"]");
                        }
                    System.out.println("0. to go back to previous page");
                    System.out.println("Choose your option: ");
                    int val=sc.nextInt();
                    sc.nextLine();
                    if(val>0 && val<=unseen.size()){
//                        System.out.println("Into unseen");
                        Message seeingmessage=unseen.get(val-1);
                        displayMesage(seeingmessage);
                        System.out.println(seeingmessage.getMessage_id());
                        messageController.updateMessage(seeingmessage.getMessage_id());
                        myMessages(user_id);
                    }
                    if(val==0){
                        myMessages(user_id);
                    }
                    else{
                        System.out.println("Enter valid input");
                        myMessages(user_id);
                    }
                }
                if(num==2){
                    for (int i=0;i<seen.size();i++){
                        System.out.println((i+1)+". [from: "+seen.get(i).getSender().getName()+"]");
                    }
                    System.out.println("0. to go back to previous page");
                    System.out.println("Choose your option: ");
                    int val=sc.nextInt();
                    sc.nextLine();
                    if(val>0 && val<=seen.size()){
                        Message seeingmessage=seen.get(val-1);
                        displayMesage(seeingmessage);
                        myMessages(user_id);
                    }
                    if(val==0){
                        myMessages(user_id);

                    }
                    else{
                        System.out.println("Enter valid input");
                        myMessages(user_id);
                    }
                }
                else {
                    myMessages(user_id);
                }

            }
            else {
                System.out.println("Enter valid options");
                myMessages(user_id);
            }

        }
        else{
            System.out.println("No new messages");
            System.out.println("1️⃣. See my inbox");
            System.out.println("2️⃣. Go to previous page");
            System.out.print("Enter your option: ");
            int res=sc.nextInt();
            sc.nextLine();
            if(res>0 && res<3){
                if (res==1){

                    for (int i=0;i<messages.size();i++){
                        System.out.println((i+1)+". [from: "+messages.get(i).getSender().getName()+"]");
                    }
                    System.out.println("0. to go back to previous page");
                    System.out.println("Choose your option: ");
                    int val=sc.nextInt();
                    sc.nextLine();
                    if(val>0 && val<=messages.size()){
                        Message seeingmessage=messages.get(val-1);
                        displayMesage(seeingmessage);
                        myMessages(user_id);
                    }
                    if(val==2){
                        associateOptions();
                    }
                    else{
                        System.out.println("Enter valid input");
                        myMessages(user_id);
                    }
                }
            }
            else {
                System.out.println("Enter valid options");
                myMessages(user_id);
            }
        }

    }
    public void displayMesage(Message message){
        System.out.println("__________________________________________________________________");
        System.out.println("From: "+message.getSender().getName());
        System.out.println("Subject: "+message.getSubject());
        System.out.println("Subject: "+message.getContent());
        System.out.println("-------------------------------------------------------------------");


    }
}





