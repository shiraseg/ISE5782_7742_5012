package primitives;

public class Material
{
    public Double3 kD=Double3.ZERO;
    public Double3 kS=Double3.ZERO;
    public Double3 kT=Double3.ZERO;//transparency - שקיפות
    public Double3 kR=Double3.ZERO;//השתקפות -reflection

    int nShininess=0;

    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;

    }

    public Material setkD(Double3 kD)
    {
        this.kD = kD;
        return this;
    }

    //for the second constructor in Double3
    public Material setkD(Double kD)
    {
        this.kD=new Double3(kD);
        return this;
    }

    public Material setkS(Double3 kS)
    {
        this.kS = kS;
        return this;
    }

    //for the second constructor in Double3
    public Material setkS(Double kS)
    {
        this.kS=new Double3(kS);
        return this;
    }

    public Material setnShininess(int nShininess)
    {
        this.nShininess = nShininess;
        return this;
    }

    public Double3 getkD()
    {
        return kD;
    }

    public Double3 getkS()
    {
        return kS;
    }

    public int getnShininess()
    {
        return nShininess;
    }
}
