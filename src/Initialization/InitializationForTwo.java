package Initialization;

import Da.Dan;
import Da.Quan;
import OCo.ODan;
import OCo.OQuan;

import java.util.ArrayList;

public class InitializationForTwo implements Initialization {

    public ArrayList<ODan> InitODan() {
        // Create an array to store the 10 ODan objects
        ArrayList<ODan> cacODan = new ArrayList<>();
        ODan oDan = null;
        for (int i = 0; i < 12; i++) {
            if (i == 5 || i == 11) {
                continue;
            }
            oDan = new ODan(i);
            cacODan.add(oDan);
        }
        return cacODan;
    }

    public ArrayList<OQuan> InitOQuan() {
        ArrayList<OQuan> oQuans = new ArrayList<>();
        OQuan oQuan = null;

        oQuan = new OQuan(5);
        oQuans.add(oQuan);
        oQuan = new OQuan(11);
        oQuans.add(oQuan);

        return oQuans;
    }

    public ArrayList<Dan> InitDan() {
        ArrayList<Dan> dans = new ArrayList<>();
        for (int id = 0; id < 50; id++) {
            dans.add(new Dan(id)); // Tạo 50 Dan với ID khác nhau
        }
        return dans;
    }

    public ArrayList<Quan> InitQuan() {
        ArrayList<Quan> quans = new ArrayList<>();
        Quan quan = null;

        quan = new Quan(5);
        quans.add(quan);
        quan = new Quan(11);
        quans.add(quan);

        return quans;
    }

//    private static void printBoard(ArrayList<ODan> oDans, ArrayList<OQuan> oQuans) {
//        System.out.print("     ");
//        for (int k = 9; k > 4; k--) {
//            System.out.print(oDans.get(k) + " ");
//        }
//        System.out.println("\n \n \n");
//
//        System.out.println(oQuans.get(1) + "                                                                                                                           " + oQuans.get(0));
//
//        System.out.println("\n \n \n");
//        System.out.print("     ");
//
//        for (int k = 0; k < 5; k++) {
//            System.out.print(oDans.get(k) + " ");
//        }
//        System.out.println("\n");
//    }


//    public static void main(String[] args) {
//        ArrayList<ODan> oDans = InitODan();
//        ArrayList<Dan> dans = InitDan();
//        ArrayList<Quan> quans = InitQuan();
//        ArrayList<OQuan> oQuans = InitOQuan();
//        int danIndex = 0;
//        for (int i = 0; i < oDans.size(); i++) {
//            for (int j = 0; j < 5 /*dans.size()*/ ; j++) {
//                oDans.get(i).setDans(dans.get(danIndex));
//                danIndex++;
//            }
//        }
//        System.out.println(oDans.get(1).getDans());
//        System.out.println((ODan.sumDans(oDans.get(1).getDans())));
//        System.out.println(oDans);
//        System.out.println(oQuans);
//
//    }

}
