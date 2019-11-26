public class Fraction{
    private int num; //state, private for better encapsulation
    private int denom;


    public Fraction(int num, int denom){ //main constructor, 2 args
        if(denom == 0){
            throw new IllegalArgumentException();//can throw exceptions if something... cool
        }
        if(denom<0){//1 and 0 arg constructors using this keyword
            denom = denom * (-1);
            num = num * (-1);
        }
        this.num = num;
        this.denom = denom;
    }
    public Fraction(int num){
        this(num,1);
    }
    public Fraction(){
        this(0,1);
    }
    public int getNumerator(){
        return this.num;
    }
    public int getDenominator(){
        return this.denom;
    }
    public String toString(){
        String frac = Integer.toString(this.num) + "/" + Integer.toString(this.denom);
        return frac;
    }
    public Double toDouble(){
        Double numer = new Double(this.num);
        Double denomer = new Double(this.denom);
        return numer/denomer;
    }
    public Fraction add(Fraction other){ //add sub mult and divide pretty straight forward
        int newnum = this.num*other.denom + other.num*this.denom;
        int newdenom = this.denom*other.denom;
        Fraction newfrac = new Fraction(newnum,newdenom);
        return newfrac;
    }
    public Fraction subtract(Fraction other){
        int newnum = this.num*other.denom - other.num*this.denom;
        int newdenom = this.denom * other.denom;
        Fraction newfrac = new Fraction(newnum,newdenom);
        return newfrac;
    }
    public Fraction multiply(Fraction other){
        Fraction newfrac  = new Fraction(this.num*other.num,this.denom*other.denom);
        return newfrac;
    }
    public Fraction divide(Fraction other){
        Fraction newfrac = new Fraction(this.num*other.denom,this.denom*other.num);
        return newfrac;
    }
    public boolean equals(Fraction other){
        this.toLowestTerms();
        other.toLowestTerms();
        int num1 = this.getNumerator();
        int num2 = other.getNumerator();
        int denom1 = this.getDenominator();
        int denom2 = other.getDenominator();
        if(num1==num2 && denom1 == denom2){
            return true;
        }
        return false;

    }
    public void toLowestTerms(){ //divides by gcd to get in lowest terms
        int gcd = gcd(Math.abs(this.num),Math.abs(this.denom));
        this.num = this.num/gcd;
        this.denom = this.denom/gcd;
    }
    public static int gcd(int num, int denom){ //uses recursive euclidean alg to find gcd
        if(denom>num){
            return gcd(denom,num);
        }
        if(num%denom == 0){
            return denom;
        }
        return gcd(denom,num%denom);

    }
}