package Initialization;

import Model.Da.*;
import Model.OCo.*;

import java.util.ArrayList;

interface Initialization {

    ArrayList<ODan> InitODan() ;

    ArrayList<OQuan> InitOQuan();

    ArrayList<Dan> InitDan();

    ArrayList<Quan> InitQuan();

}