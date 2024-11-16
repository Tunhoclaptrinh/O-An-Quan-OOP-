package KhoiTao;

import Da.Dan;
import Da.Quan;
import OCo.ODan;
import OCo.OQuan;
import GameGUI.*;

import java.util.HashMap;
import java.util.ArrayList;

public class KhoiTao {

    public static ArrayList<ODan> KhoiTaoODan() {
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

    public static ArrayList<OQuan> KhoiTaoOQuan() {
        ArrayList<OQuan> oQuans = new ArrayList<>();
        OQuan oQuan = null;

        oQuan = new OQuan(5);
        oQuans.add(oQuan);
        oQuan = new OQuan(11);
        oQuans.add(oQuan);

        return oQuans;
    }

//    public static ArrayList<Dan> KhoiTaoDan() {
//        // Create an array to store the 50 Dan objects
//        ArrayList<Dan> dans = new ArrayList<>();
//        Dan dan = null;
//
//         //Create 50 Dan objects with different indices
//        for (int j = 0; j < 12; j++) {
//            if (j == 5 || j == 11) {
//                continue;
//            } else {
//                for (int i = 0; i < ODan.DEFAULT_STONES; i++) {
//                    dan = new Dan(j);
//                    dans.add(dan);
//                }
//            }
//        }
//        return dans;
//    }
    public static ArrayList<Dan> KhoiTaoDan() {
        ArrayList<Dan> dans = new ArrayList<>();
        for (int id = 0; id < 50; id++) {
            dans.add(new Dan(id)); // Tạo 50 Dan với ID khác nhau
        }
        return dans;
    }

    public static ArrayList<Quan> KhoiTaoQuan() {
        ArrayList<Quan> quans = new ArrayList<>();
        Quan quan = null;

        quan = new Quan(5);
        quans.add(quan);
        quan = new Quan(11);
        quans.add(quan);

        return quans;
    }

    private static void printBoard(ArrayList<ODan> oDans, ArrayList<OQuan> oQuans) {
        System.out.print("     ");
        for (int k = 9; k > 4; k--) {
            System.out.print(oDans.get(k) + " ");
        }
        System.out.println("\n \n \n");

        System.out.println(oQuans.get(1) + "                                                                                                                           " + oQuans.get(0));

        System.out.println("\n \n \n");
        System.out.print("     ");

        for (int k = 0; k < 5; k++) {
            System.out.print(oDans.get(k) + " ");
        }
        System.out.println("\n");
    }

//    public static HashMap<Integer, Integer> calculateStonesWithSameIndex(ArrayList<Dan> listOfDa) {
//        HashMap<Integer, Integer> countMap = new HashMap<>();
//
//        // Duyệt qua danh sách các đá và đếm số lượng đá có cùng vị trí index
//        for (Dan da : listOfDa) {
//            int index = da.getIndex();
//            countMap.put(index, countMap.getOrDefault(index, 0) + 1);
//        }
//
//        return countMap;
//    }


    public static void main(String[] args) {
        ArrayList<ODan> oDans = KhoiTaoODan();
        ArrayList<Dan> dans = KhoiTaoDan();
        ArrayList<Quan> quans = KhoiTaoQuan();
        ArrayList<OQuan> oQuans = KhoiTaoOQuan();
        int danIndex = 0;
        for (int i = 0; i < oDans.size()  ; i++) {
            for (int j = 0; j < 5 /*dans.size()*/ ; j++) {
                oDans.get(i).setDans(dans.get(danIndex));
                danIndex++;
            }
        }
        System.out.println(oDans.get(1).getDans());
        System.out.println((ODan.sumDans(oDans.get(1).getDans())));
        System.out.println(oDans);
        System.out.println(oQuans);




//        printBoard();

//        // Gọi hàm tính toán
//        HashMap<Integer, Integer> numberStones = calculateStonesWithSameIndex(dans);
//
//        // In ra kết quả
//        for (int index : numberStones.keySet()) {
//            System.out.println("Index: " + index + ", Number of Stones: " + numberStones.get(index));
//        }
    }
}
