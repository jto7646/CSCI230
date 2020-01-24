import java.lang.*;

public class Triangle implements polygon{ 

private int vec1, vec2, vec3, area, perim;

public void Triangle(){
    this.vec1 = 0;
    this.vec2 = 0;
    this.vec3 = 0;
}

public void Triangle(int vec1, int vec2, int vec3){
    this.vec1 = vec1;
    this.vec2 = vec2;
    this.vec3 = vec3;
}

public int Area(){
    
   /* if (this.perim == 0){
        System.out.println("No Perimeter.");
        return 0;
    } */
    
    int p = this.perim;
    p /= 2;
    
    this.area = (int) Math.sqrt(p*(p-this.vec1)*(p-this.vec2)*(p-this.vec3));

    return(this.area);
}

public int Perimeter(int vec1, int vec2, int vec3){
    
    this.vec1 = vec1;
    this.vec2 = vec2; 
    this.vec3 = vec3;

    setPerim(vec1 + vec2 + vec3);

    System.out.println("perimeter");
    return(getPerim());
}

public int getPerim(){
    return(perim);
}

public void setPerim(int perim){
    this.perim = perim;
}

}