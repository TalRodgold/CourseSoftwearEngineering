package primitives;

public class Material {
    /**
     * default values
     */
    public Double3 kD = new Double3(0);
    public Double3 kS = new Double3(0);
    public int nShininess = 0;

    /**
     * set using builder pattern method
     * @param kD = Double3
     * @return
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * set using builder pattern method
     * @param kS = Double3
     * @return
     */
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * set using builder pattern method
     * @param nShininess = int
     * @return
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * set using builder pattern method
     * @param kD = Double
     * @return
     */
    public Material setkD(Double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * set using builder pattern method
     * @param kS = Double
     * @return
     */
    public Material setkS(Double kS) {
        this.kS = new Double3(kS);
        return this;
    }

}
