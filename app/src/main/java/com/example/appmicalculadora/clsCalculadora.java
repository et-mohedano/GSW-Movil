package com.example.appmicalculadora;

public class clsCalculadora {
    // Atributos

    private double operando1;
    private double operando2;
    private double resultado;
    private String operador;

    // Propiedades get(), set() de los atributos

    public double getOperando1(){
        return this.operando1;
    }
    public double getOperando2(){
        return this.operando2;
    }
    public double getResultado(){
        return this.resultado;
    }
    public String getOperador(){
        return this.operador;
    }
    public void setOperando1(double op1){
        this.operando1 = op1;
    }
    public void setOperando2(double op2){
        this.operando2 = op2;
    }
    public void setResultado(double res){
        this.resultado = res;
    }
    public void setOperador(String oper){
        this.operador = oper;
    }

    // Metodos de funcionalidad

    public void sumar(){
        this.operador = "+";
    }
    public void restar(){
        this.operador = "-";
    }
    public void multiplicar(){
        this.operador = "*";
    }
    public void dividir(){
        this.operador = "/";
    }
    public void potencia(){
        this.operador = "^";
    }
    public void operaciones(){
        switch (this.operador){
            case "+":
                this.resultado = this.operando1 + this.operando2;
                break;
            case "-":
                this.resultado = this.operando1 - this.operando2;
                break;
            case "*":
                this.resultado = this.operando1 * this.operando2;
                break;
            case "/":
                this.resultado = this.operando1 / this.operando2;
                break;
            case "^":
                this.resultado = Math.pow(this.operando1,this.operando2);
                break;
        }
    }
    public void limpiar(){
        this.operando1 = 0;
        this.operando2 = 0;
        this.resultado = 0;
        this.operador = "";
    }
}
