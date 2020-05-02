import java.util.*;
import java.util.HashMap;
import java.util.Map.Entry;


public class Simpul implements Comparable < Simpul >{
  public int rootDist;
  public Simpul[] parent;
  public Integer cost;
  public boolean dariAsal;
  public HashMap<String, Integer> perahu;
  public HashMap<String, Integer> tempatAsal;
  public HashMap<String, Integer> tempatTujuan;
  
  public boolean getDariAsal(){
    return dariAsal;
  }

  public int getAllParentSize(){
    if(this.parent.length == 0){
      return 0;
    }
    else{
      Simpul temp = this.parent[0];
      int n = 1;
      while(temp.parent.length != 0){
        temp = temp.parent[0];
        n += 1;
      }
      return n;
    }

    

  }

  public Integer getCost(){
    return cost;
  }

  //konstruktor ini jangan dihiraukan hanya untuk test saja
  public Simpul(int cost){
    this.rootDist = 0;
    this.parent = new Simpul[]{};
    this.cost = cost;
  }

  public Simpul(int rootDist, boolean dariAsal, HashMap<String, Integer> perahuVal, Simpul[] parentVal){
    this.rootDist = rootDist;
    this.parent = parentVal;
    this.cost = 0;
    this.dariAsal = dariAsal;
    this.perahu = perahuVal;
    if(parentVal.length > 0){
      this.tempatAsal = new HashMap<>();
      this.tempatTujuan = new HashMap<>();
      this.tempatAsal.put("Sheep",parentVal[0].tempatAsal.get("Sheep"));
      this.tempatAsal.put("Wolf",parentVal[0].tempatAsal.get("Wolf"));
      this.tempatAsal.put("Farmer",parentVal[0].tempatAsal.get("Farmer"));
      this.tempatTujuan.put("Sheep",parentVal[0].tempatTujuan.get("Sheep"));
      this.tempatTujuan.put("Wolf",parentVal[0].tempatTujuan.get("Wolf"));
      this.tempatTujuan.put("Farmer",parentVal[0].tempatTujuan.get("Farmer"));
    }
    else{
      this.tempatAsal = new HashMap<>();
      this.tempatTujuan = new HashMap<>();
      this.tempatAsal.put("Sheep",2);
      this.tempatAsal.put("Wolf",1);
      this.tempatAsal.put("Farmer",1);
      this.tempatTujuan.put("Sheep",0);
      this.tempatTujuan.put("Wolf",0);
      this.tempatTujuan.put("Farmer",0);
    }
    if (dariAsal) {
      for (Entry<String, Integer> m : this.tempatAsal.entrySet()) 
      {  
        this.cost += m.getValue()-this.perahu.get(m.getKey());
      }
      //this.cost += this.rootDist;  
    }
    else{
      //System.out.println("dds");
      for (Entry<String, Integer> m : this.tempatAsal.entrySet()) 
      { 
        this.cost += m.getValue()+this.perahu.get(m.getKey());
      }   
      //this.cost += this.rootDist;
    }
  }

  /*
  public boolean isSolusi(){
    if(!dariAsal){
      return false;
    }
    else{
      for(Map.Entry<String,Integer> m : this.tempatAsal.entrySet()){
        if(m.getValue() != 0){
          return false;
        }
      }
      return true;
    }
  }
  */

  public void printLintasan(){
    int n = 1;
    ArrayList<Simpul> arr = new ArrayList<>();
    Simpul temp = this; 
    boolean stop = false;
    while(!stop){
      arr.add(temp);
      //temp.printSimpul();
      if(temp.parent.length == 0){
        stop = true;
      }
      else{
        temp = temp.parent[0];
      }
    }
    for(int i=arr.size()-1; i>=0; i--){
      System.out.println("Lintasan ke- " + n);
      n += 1;
      Simpul temp2 = arr.get(i);
      System.out.println(temp2);
      //temp2.printSimpul();
      if(temp2.getDariAsal()){
        System.out.println("Dari tempat asal ke tempat tujuan");
      }
      else{
        System.out.println("Dari tempat tujuan ke tempat asal");
      }
      System.out.println("Cost simpul ini adalah "+temp2.getCost() + "\n");
    }

  }

  public void generateChild(){
    Integer jmlSheepPerahuParent = this.perahu.get("Sheep");
    Integer jmlWolfPerahuParent = this.perahu.get("Wolf"); 
    Integer jmlFarmerPerahuParent = this.perahu.get("Farmer");
    // ketiga variabel di atas diperlukan agar kita tidak bulak balik dengan isi perahu yang sama percis

    if(!(jmlSheepPerahuParent==0 && jmlWolfPerahuParent==0 && jmlFarmerPerahuParent==1)){
      HashMap<String,Integer> perahuBaru = new HashMap<>();
      perahuBaru.put("Sheep",0);
      perahuBaru.put("Wolf",0);
      perahuBaru.put("Farmer",1);
      Riddle.arr.add(new Simpul(this.rootDist+1, !this.dariAsal, perahuBaru, new Simpul[]{this}));
    }
      //Collections.sort(arr);
    if(!(jmlSheepPerahuParent==0 && jmlWolfPerahuParent==1 && jmlFarmerPerahuParent==1)){
      HashMap<String,Integer> perahuBaru2 = new HashMap<>();
      perahuBaru2.put("Sheep",0);
      perahuBaru2.put("Wolf",1);
      perahuBaru2.put("Farmer",1);
      Riddle.arr.add(new Simpul(this.rootDist+1, !this.dariAsal, perahuBaru2, new Simpul[]{this}));
    }
      //Collections.sort(arr);

    if(!(jmlSheepPerahuParent==1 && jmlWolfPerahuParent==0 && jmlFarmerPerahuParent==1)){
      HashMap<String,Integer> perahuBaru3 = new HashMap<>();
      perahuBaru3.put("Sheep",1);
      perahuBaru3.put("Wolf",0);
      perahuBaru3.put("Farmer",1);
      Riddle.arr.add(new Simpul(this.rootDist+1, !this.dariAsal, perahuBaru3, new Simpul[]{this}));
    }
      //Collections.sort(arr);
    /*
    System.out.println("Sebelum diurutkan:Ini aku ");
    for(int i=0; i<Riddle.arr.size(); i++){
      System.out.println("Jajajaja");
      //Riddle.arr.get(i).printLintasan();
      System.out.println(Riddle.arr.get(i).getCost());
    }
    */
    Collections.sort(Riddle.arr);
    /*
    System.out.println("Setelah diurutkan:Ini kamu ");
    for(int j=0; j<Riddle.arr.size(); j++){
      System.out.println("Jajajaja");
      //Riddle.arr.get(j).printLintasan();
      System.out.println(Riddle.arr.get(j).getCost());
    }
    */
  }

  public void ekspansi(){
    if(this.dariAsal){
      for(Entry<String, Integer> m: this.perahu.entrySet()){
        this.tempatTujuan.put(m.getKey(),m.getValue()+this.tempatTujuan.get(m.getKey()));
        this.tempatAsal.put(m.getKey(),this.tempatAsal.get(m.getKey())-m.getValue());
      }
    }
    else{
      for(Entry<String, Integer> m: this.perahu.entrySet()){
        this.tempatAsal.put(m.getKey(),m.getValue()+this.tempatAsal.get(m.getKey()));
        this.tempatTujuan.put(m.getKey(),this.tempatTujuan.get(m.getKey())-m.getValue());
      }
    }
    this.generateChild();
  }

  @Override
  public int compareTo(Simpul o) {
    Integer temp1 = -1*this.getAllParentSize();
    Integer temp2 = -1*o.getAllParentSize();
    int temp;
    temp = this.getCost().compareTo(o.getCost());
    if(temp == 0){
      temp = temp1.compareTo(temp2);
    }
    return temp;
  }


  @Override
  public String toString(){
    String temp = "\n Ini Perahu \n";
    for(Map.Entry<String, Integer> m : this.perahu.entrySet()){
      temp += String.valueOf(m.getKey() + " " + m.getValue() + " ");
    }
    temp += "\n Ini Tempat Asal \n";
    for(Map.Entry<String, Integer> m :this.tempatAsal.entrySet()){
      temp += String.valueOf(m.getKey() + " " + m.getValue() + " ");
    }
    temp += "\n Ini Tempat Tujuan \n";
    for(Map.Entry<String, Integer> m :this.tempatTujuan.entrySet()){
      temp += String.valueOf(m.getKey() + " " + m.getValue() + " ");
    }
    return temp;
  }

  public void tesTambah(ArrayList<Simpul> arr){
    arr.add(new Simpul(2));
    Collections.sort(arr);
  }

  public static void main(String[] args){
    /*
    HashMap<String, Integer> temp = new HashMap<>();
    temp.put("Sheep",2);
    temp.put("Farmer",1);
    temp.put("Wolf",1);
    //System.out.println("Heya");
    Simpul s1 = new Simpul(0, true, temp, new Simpul[]{});
    System.out.println(s1.isSolusi());
    */

    int n = 0;
    ArrayList<Simpul> arr = new ArrayList<>();
    HashMap<String, Integer> temp = new HashMap<>();
    temp.put("Sheep",0);
    temp.put("Farmer",1);
    temp.put("Wolf",1);
    //System.out.println("Heya");
    Simpul s1 = new Simpul(0, true, temp, new Simpul[]{});
    arr.add(s1);
    HashMap<String, Integer> temp2 = new HashMap<>();
    temp2.put("Sheep",1);
    temp2.put("Farmer",1);
    temp2.put("Wolf",1);
    //System.out.println("Heya");
    Simpul s2 = new Simpul(0, true, temp2, new Simpul[]{});
    arr.add(s2);
    HashMap<String, Integer> temp3 = new HashMap<>();
    temp3.put("Sheep",2);
    temp3.put("Farmer",1);
    temp3.put("Wolf",1);
    
    //System.out.println("Heya");
    Simpul s3 = new Simpul(0, true, temp3, new Simpul[]{});
    arr.add(s3);
    
    HashMap<String, Integer> temp4 = new HashMap<>();
    temp4.put("Sheep",0);
    temp4.put("Farmer",0);
    temp4.put("Wolf",0);
    
    //System.out.println("Heya");
    Simpul s4 = new Simpul(0, true, temp4, new Simpul[]{s3});
    s4.cost = 1;
    arr.add(s4);


    Collections.sort(arr);
    while(arr.size() > 0){
      System.out.println(arr.get(0));
      System.out.println(arr.get(0).cost);
      arr.remove(0);
      /*
      if(n < 3){
        s1.tesTambah(arr);
      }
      */
      n ++;
    }


  }



}