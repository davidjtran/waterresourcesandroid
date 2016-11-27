package davditran.waterresources.model;
import android.content.Context;

import java.io.*;
import java.util.ArrayList;

import davditran.waterresources.MainScreenActivity;

public class SerializationController implements Serializable {

    private static SerializationController controller;
    public static ArrayList<Report> reports;
    public static ArrayList<User> users;
    public static ArrayList<WaterQualityReport> waterQualityReports;

    public SerializationController() {
        reports = new ArrayList<>();
        users = new ArrayList<>();
        waterQualityReports = new ArrayList<>();
        // set singleton
        controller = this;
    }

    public static SerializationController getInstance() {
        if (controller == null) {
            controller = new SerializationController();
        }
        return controller;
    }

    public void saveAll(Context context) {
        //saveChanges("admins", admins);
        //saveChanges("managers", managers);
        //saveChanges("registeredUsers", registeredUsers);
        saveChanges(context, "reports", reports);
        //saveChanges("submittedQualityReports", submittedQualityReports);
        //saveChanges("submittedReports", submittedReports);
        saveChanges(context, "users", users);
        saveChanges(context, "waterQualityReports", waterQualityReports);
        //saveChanges("workers", workers);
    }

    public void retrieveAll(Context context) {
        //admins = (ArrayList<Admin>)retrieveChanges("admins");
        //noinspection unchecked
        //managers = (ArrayList<Manager>)retrieveChanges("managers");
        //registeredUsers = (ArrayList<RegisteredUser>)retrieveChanges("registeredUsers");
        //noinspection unchecked
        reports = (ArrayList<Report>) retrieveChanges(context, "reports");
        //submittedQualityReports = (ArrayList<SubmittedQualityReports>)retrieveChanges("submittedQualityReports");
        //submittedReports = (ArrayList<SubmittedReports>)retrieveChanges("submittedReports");
        //noinspection unchecked
        users = (ArrayList<User>) retrieveChanges(context, "users");
        //noinspection unchecked
        waterQualityReports = (ArrayList<WaterQualityReport>) retrieveChanges(context, "waterQualityReports");
        //workers = (ArrayList<Worker>)retrieveChanges("workers");
    }

    //SAVE-------------------------------------------------------------------------------------------
    public void saveChanges(Context context, String fileName, ArrayList<? extends Serializable> list) {
        try {
            FileOutputStream fileOut = context.openFileOutput(fileName + ".ser", Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            //System.out.println(list.get(0));
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in ./" + fileName + ".ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    //RETRIEVE-------------------------------------------------------------------------------------------
    public ArrayList<? extends Serializable> retrieveChanges(Context context, String fileName) {
        ArrayList<? extends Serializable> list;
        try {
            FileInputStream fileIn = context.openFileInput(fileName + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            //noinspection unchecked
            list = (ArrayList<? extends Serializable>) in.readObject();
            in.close();
            fileIn.close();
            System.out.printf("Serialized data retrieved from ./" + fileName + ".ser (%d objects)\n", list.size());
            return list;
        } catch (IOException i) {
            System.out.println("IO exception");
            i.printStackTrace();
            return new ArrayList<>();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return new ArrayList<>();
        }
    }
}