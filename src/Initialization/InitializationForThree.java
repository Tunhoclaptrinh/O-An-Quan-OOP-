package Initialization;

import Da.Dan;
import Da.Quan;
import OCo.ODan;
import OCo.OQuan;

import java.util.ArrayList;

public class InitializationForThree implements Initialization {

    public ArrayList<ODan> InitODan() {
        // Create an array to store the 15 ODan objects
        ArrayList<ODan> cacODan = new ArrayList<>();
        ODan oDan = null;
        for (int i = 0; i < 18; i++) {
            if (i == 5 || i == 11 || i == 17) {
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
        oQuan = new OQuan(17);
        oQuans.add(oQuan);

        return oQuans;
    }

    // Tạo 50 Dan với ID khác nhau
    public ArrayList<Dan> InitDan() {
        ArrayList<Dan> dans = new ArrayList<>();
        for (int id = 0; id < 75; id++) {
            dans.add(new Dan(id));
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
        quan = new Quan(17);
        quans.add(quan);

        return quans;
    }

}

