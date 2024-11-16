//package Mainnn;
//
//import Da.Dan;
//import Da.Quan;
//import OCo.ODan;
//import OCo.OQuan;
//import GameGUI.*;
//
//import java.util.HashMap;
//import java.util.ArrayList;
//
////public class Main1 {
////
////    public static ArrayList<ODan> KhoiTaoODan(){
////        // Create an array to store the 10 ODan objects
////        ArrayList<ODan> cacODan = new ArrayList<>();
////        ODan oDan = null;
////        for (int i = 0; i < 12; i++) {
////            if (i == 5 || i == 11) {
////                continue;
////            }
////            oDan = new ODan(i, 5,308,421);
////            cacODan.add(oDan);
////        }
////        return cacODan;
////    }
////
////    public static ArrayList<OQuan> KhoiTaoOQuan (){
////        ArrayList<OQuan> cacOQuan = new ArrayList<>();
////        OQuan oQuan = null;
////
////        oQuan = new OQuan(5);
////        cacOQuan.add(oQuan);
////        oQuan = new OQuan(11);
////        cacOQuan.add(oQuan);
////
////        return cacOQuan;
////    }
////
////    public static ArrayList<Dan> KhoiTaoDan(){
////            // Create an array to store the 50 Dan objects
////            ArrayList <Dan> cacDan = new ArrayList<>();
////            Dan dan = null;
////
////            // Create 50 Dan objects with different indices
////            for (int j = 0; j < 12; j++) {
////                if (j == 5 || j == 11) {
////                    continue;
////                }
////                else {
////                    for (int i = 0; i < 5; i++) {
////                        dan = new Dan(j);
////                        cacDan.add(dan);
////                    }
////                }
////            }
////            return cacDan;
////    }
////
////    public static ArrayList<Quan> KhoiTaoQUan(){
////        ArrayList <Quan> cacQuan = new ArrayList<>();
////        Quan quan = null;
////
////        quan = new Quan(5);
////        cacQuan.add(quan);
////        quan = new Quan(11);
////        cacQuan.add(quan);
////
////        return cacQuan;
////    }
//////    public ArrayList KhoiTaoBanCo (){
//////
//////        return null;
//////    }
////
////    private static void printBoard() {
////        ArrayList cacODan = KhoiTaoODan();
////        ArrayList cacOQuan = KhoiTaoOQuan();
////
////        System.out.print("     ");
////        for (int k = 9; k > 4; k--) {
////            System.out.print(cacODan.get(k) + " ");
////        }
////        System.out.println("\n \n \n");
////
////        System.out.println(cacOQuan.get(1) + "                                                                                                                           " + cacOQuan.get(0));
////
////        System.out.println("\n \n \n");
////        System.out.print("     ");
////
////        for (int k = 0; k < 5; k++) {
////            System.out.print(cacODan.get(k) + " ");
////        }
////        System.out.println("\n");
////    }
////
////    public static HashMap<Integer, Integer> calculateStonesWithSameIndex(ArrayList listOfDa){
////        HashMap<java.lang.Integer, java.lang.Integer> countMap = new HashMap<>();
////
////        // Duyệt qua danh sách các đá và đếm số lượng đá có cùng vị trí index
////        for ( da : listOfDa) {
////            int index = da.getIndex();
////            countMap.put(index, countMap.getOrDefault(index, 0) + 1);
////        }
////
////        return countMap;
////
////    }
////
////
////
////    public static void main(String[] args) {
////        ArrayList<ODan> cacODan = KhoiTaoODan();
////        ArrayList<Dan> cacDan = KhoiTaoDan();
////
////        // Gọi hàm tính toán
////        HashMap<Integer, Integer> result = calculateStonesWithSameIndex(cacDan);
////
////        // In ra kết quả
////        for (int index : result.keySet()) {
////            System.out.println("Index: " + index + ", Number of Stones: " + result.get(index));
////        }
////
////
////        System.out.println(cacODan);
////        System.out.println(cacODan.size());
//////        System.out.println(cacODan.get(0));
////        System.out.println(cacDan);
////        System.out.println(cacDan.size());
//////        System.out.println(cacDan.get(0));
//////        ArrayList odanchua = odan.KhoiTao();
//////        System.out.println(cacODan.get(1));
////        printBoard();
////    }
////}
//
//
//public class Main1 {
//
//    public static ArrayList<ODan> KhoiTaoODan() {
//        // Create an array to store the 10 ODan objects
//        ArrayList<ODan> cacODan = new ArrayList<>();
//        ODan oDan = null;
//        for (int i = 0; i < 12; i++) {
//            if (i == 5 || i == 11) {
//                continue;
//            }
//            oDan = new ODan(i, 5);
//            cacODan.add(oDan);
//        }
//        return cacODan;
//    }
//
//    public static ArrayList<OQuan> KhoiTaoOQuan() {
//        ArrayList<OQuan> cacOQuan = new ArrayList<>();
//        OQuan oQuan = null;
//
//        oQuan = new OQuan(5);
//        cacOQuan.add(oQuan);
//        oQuan = new OQuan(11);
//        cacOQuan.add(oQuan);
//
//        return cacOQuan;
//    }
//
//    public static ArrayList<Dan> KhoiTaoDan() {
//        // Create an array to store the 50 Dan objects
//        ArrayList<Dan> cacDan = new ArrayList<>();
//        Dan dan = null;
//
//        // Create 50 Dan objects with different indices
//        for (int j = 0; j < 12; j++) {
//            if (j == 5 || j == 11) {
//                continue;
//            } else {
//                for (int i = 0; i < 5; i++) {
//                    dan = new Dan(/*j*/);
//                    cacDan.add(dan);
//                }
//            }
//        }
//        return cacDan;
//    }
//
//    public static ArrayList<Quan> KhoiTaoQuan() {
//        ArrayList<Quan> cacQuan = new ArrayList<>();
//        Quan quan = null;
//
//        quan = new Quan(5);
//        cacQuan.add(quan);
//        quan = new Quan(11);
//        cacQuan.add(quan);
//
//        return cacQuan;
//    }
//
//    private static void printBoard() {
//        ArrayList<ODan> cacODan = KhoiTaoODan();
//        ArrayList<OQuan> cacOQuan = KhoiTaoOQuan();
//
//        System.out.print("     ");
//        for (int k = 9; k > 4; k--) {
//            System.out.print(cacODan.get(k) + " ");
//        }
//        System.out.println("\n \n \n");
//
//        System.out.println(cacOQuan.get(1) + "                                                                                                                           " + cacOQuan.get(0));
//
//        System.out.println("\n \n \n");
//        System.out.print("     ");
//
//        for (int k = 0; k < 5; k++) {
//            System.out.print(cacODan.get(k) + " ");
//        }
//        System.out.println("\n");
//    }
//
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
//
//    public static void main(String[] args) {
//        ArrayList<ODan> cacODan = KhoiTaoODan();
//        ArrayList<Dan> cacDan = KhoiTaoDan();
//
//        // Gọi hàm tính toán
//        HashMap<Integer, Integer> numberStones = calculateStonesWithSameIndex(cacDan);
//
//        // In ra kết quả
//        for (int index : numberStones.keySet()) {
//            System.out.println("Index: " + index + ", Number of Stones: " + numberStones.get(index));
//        }
//
//        System.out.println(cacODan);
//        System.out.println(cacODan.size());
//        System.out.println(cacDan);
//        System.out.println(cacDan.size());
//
//        printBoard();
//    }
//}
