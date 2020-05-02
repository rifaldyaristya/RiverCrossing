import java.util.*;

public class Riddle{
  public static ArrayList<Simpul> arr = new ArrayList<Simpul>();

  public static boolean isValidSimpul(Simpul s){
    int jmlDomba;
    int jmlSerigala;
    if(s.dariAsal){
      //cek apakah jika dilakukan di tempat asal domba bisa dimakan serigala
      jmlDomba = s.tempatAsal.get("Sheep") - s.perahu.get("Sheep");
      jmlSerigala = s.tempatAsal.get("Wolf") - s.perahu.get("Wolf");
      if((jmlDomba > 0 && jmlSerigala > 0) || jmlDomba < 0 || jmlSerigala < 0){
        return false;
      }
      else{
        return true;
      }
    }
    else{
      //cek apakah jika dilakukan di tempat tujuan domba bisa dimakan serigala
      jmlDomba = s.tempatTujuan.get("Sheep") - s.perahu.get("Sheep");
      jmlSerigala = s.tempatTujuan.get("Wolf") - s.perahu.get("Wolf");
      if(jmlDomba > 0 && jmlSerigala > 0 || jmlDomba < 0 || jmlSerigala < 0){
        return false;
      }
      else{
        return true;
      }
    }
  }

  /*
  public int pickIndex(){
    // Mencari dari cost terkecil yang nilai nnya paling tinggi (sudah mendekati solusi)
    int temp = 0;
    int costTemp = arr
  }
  */

  public static void main(String[] args){
    // Pada awalnya petani hanya punya 1 pilihan yaitu menyeberang dengan seekor serigala, maka simpul ini yang akan menjadi simpul akar
    int jumlahEkspansi = 0;
    boolean isSolutionExist = false;
    HashMap<String, Integer> temp = new HashMap<>();
    temp.put("Sheep",0);
    temp.put("Farmer",0);
    temp.put("Wolf",0);
    //System.out.println("Heya");
    Simpul s1 = new Simpul(0, false, temp, new Simpul[]{});
    arr.add(s1);

    while(arr.size() > 0){
      /*
      System.out.println("Ekspansi ke- " + jumlahEkspansi);
      
      for(int j=0; j<arr.size(); j++){
        System.out.println("Jajajaja");
        System.out.println("cost: " + Riddle.arr.get(j).getCost());
        System.out.println("jumlah parrent "+ Riddle.arr.get(j).parent.length);
      }
      */
      jumlahEkspansi ++;
      Simpul temp2 = arr.get(0);
      arr.remove(0);
      if(isValidSimpul(temp2)){
        temp2.ekspansi();
        if(temp2.cost == 0){
          isSolutionExist = true;
          System.out.println("Solusi ketemu!! Ini solusinya:\n");
          temp2.printLintasan();
          System.out.println("\nJumlah ekspansi simpul sampai menemukan solusi: " + jumlahEkspansi);
          break;
        }
      }
      
    }

    if(!isSolutionExist){
      System.out.println("Solusi tidak ditemukan");
    }
    

    //for(int i=0 ; i<20; i++){
    //  Simpul temp2 = arr.get(0);
    //  arr.remove(0);
    //  System.out.println("Yahallo");
    //  
    //  //System.out.println("BCL Kecewa" + temp2.cost);
    //  temp2.ekspansi();
    //  temp2.printLintasan();
    //  /*
    //  System.out.println("Ini di dlm file Riddle");
    //  for(int j=0; j<arr.size(); j++){
    //    System.out.println("Jajajaja");
    //    //Riddle.arr.get(j).printLintasan();
    //    System.out.println(Riddle.arr.get(j).getCost());
    //  }
    //  */
    //  //Collections.sort(arr);
    //}
    /*
    int n = 1;
    arr.get(0).ekspansi();
    arr.get(1).ekspansi();
    for(int i=0; i<arr.size(); i++){
      System.out.println("Ini Simpul ke - " + n);
      n += 1;
      arr.get(i).printLintasan();
    }
    */
    
    
    /*
    for(int i=0; i<arr.size(); i++){
      System.out.println("Watchout!!");
      arr.get(i).printSimpul();
    }
    */
  }

}