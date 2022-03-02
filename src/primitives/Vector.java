package primitives;

public class Vector extends Point{
    public Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector 0");
    }

    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (x == 0 && y == 0 && z == 0)
            throw new IllegalArgumentException("Vector 0");
    }
    public Vector add(Vector v){
        return new Vector(this.xyz.add(v.xyz));
    }
    public Vector scale(double d){
        return new Vector(this.xyz.scale(d));
    }
    public double dotProduct(Vector v){
        Double3 product =  this.xyz.product(v.xyz);
        return product.d1 + product.d2 + product.d3;
    }
    public Vector crossProduct(Vector v){
        double one = (this.xyz.d2 * v.xyz.d3) - (this.xyz.d3 * v.xyz.d2);
        double two = -(this.xyz.d1 * v.xyz.d3) + (this.xyz.d3 * v.xyz.d1);
        double three = (this.xyz.d1 * v.xyz.d2) - (this.xyz.d2 * v.xyz.d1);
        return new Vector(one, two, three);
    }
    public double lengthSquared(){
        return (this.xyz.d1 * this.xyz.d1) + (this.xyz.d2 * this.xyz.d2) + (this.xyz.d3 * this.xyz.d3);
    }
    public double length(){
        return Math.sqrt(this.lengthSquared());
    }
    public Vector normalize(){
        double length = Math.sqrt((this.xyz.d1 * this.xyz.d1) + (this.xyz.d2 * this.xyz.d2) + (this.xyz.d3 * this.xyz.d3));
        if (length == 0){
            throw new ArithmeticException();
        }
        return  new Vector(this.xyz.d1 / length, this.xyz.d2 / length, this.xyz.d3 / length);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }
}
