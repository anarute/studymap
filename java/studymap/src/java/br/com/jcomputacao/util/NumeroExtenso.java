/*
 * NumeroExtenso.java
 */
package br.com.jcomputacao.util;

/**
 * 15 de Setembro de 2006, 12:53
 * @author Murilo
 */
public abstract class NumeroExtenso {

    public final static int FINANCEIRO = 1;
    public final static int REAL = 2;

    public static String descreva(double numero, int tipo) {
        String retorno = "";
        int parte = 0;
        boolean primeiro = true;

        if (((int) numero / 1000000000) > 0) {
            if (primeiro) {
                primeiro = false;
            } else {
                retorno += " E ";
            }
            parte = (int) numero / 1000000000;
            numero = numero % 1000000000;
            if (tipo == FINANCEIRO) {
                String aux = NumberUtil.decimal(numero);
                numero = Double.parseDouble(aux.replace(",", "."));
            }

            if (parte == 1) {
                retorno += "UM BILHAO";
            } else {
                retorno += descrevaCentena(parte) + " BILHOES";
            }
        }

        if (((int) numero / 1000000) > 0) {
            if (primeiro) {
                primeiro = false;
            } else {
                retorno += " E ";
            }
            parte = (int) numero / 1000000;
            numero = numero % 1000000;
            if (tipo == FINANCEIRO) {
                String aux = NumberUtil.decimal(numero);
                numero = Double.parseDouble(aux.replace(",", "."));
            }

            if (parte == 1) {
                retorno += "UM MILHAO";
            } else {
                retorno += descrevaCentena(parte) + " MILHOES";
            }
        }
        if (((int) numero / 1000) > 0) {
            if (primeiro) {
                primeiro = false;
            } else {
                retorno += " E ";
            }

            parte = (int) numero / 1000;
            numero = numero % 1000;
            if (tipo == FINANCEIRO) {
                String aux = NumberUtil.decimal(numero);
                numero = Double.parseDouble(aux.replace(",", "."));
            }
            if (parte == 1) {
                retorno += "UM MIL";
            } else {
                retorno += descrevaCentena(parte) + " MIL";
            }
        }
        if (numero > 0) {
            if (primeiro) {
                primeiro = false;
            } else {
                retorno += " E ";
            }
            parte = (int) numero;
            if (parte == 1) {
                retorno += "UM";
            } else {
                retorno += descrevaCentena(parte);
            }
        }

        if (tipo == FINANCEIRO) {
            if (retorno.equals("UM")) {
                retorno += " REAL";
            } else {
                retorno += " REAIS";
            }
        }
        numero *= 100;
        numero %= 100;
        numero = Math.round(numero);
        //parte = (((int)(numero))%100);
        parte = ((int) (numero));
        if (parte != 0) {
            if (primeiro) {
                primeiro = false;
            } else {
                retorno += " E ";
            }
            if (parte == 1) {
                retorno += "UM ";
                if (tipo == FINANCEIRO) {
                    retorno += "CENTAVO";
                } else {
                    retorno += "DECIMO";
                }
            } else {
                retorno += descrevaCentena(parte);
                if (tipo == FINANCEIRO) {
                    retorno += " CENTAVOS";
                } else {
                    retorno += " DECIMOS";
                }
            }
        }
        return retorno;
    }

    private static String descrevaCentena(int parte) {
        String retorno = "";
        int fracao = parte / 100;
        parte = parte % 100;
        boolean primeiro = true;
        if (fracao != 0) {
            primeiro = false;
        }
        switch (fracao) {
            case 1:
                if (parte == 0) {
                    retorno += "CEM";
                } else {
                    retorno += "CENTO";
                }
                break;
            case 2:
                retorno += "DUZENTOS";
                break;
            case 3:
                retorno += "TREZENTOS";
                break;
            case 4:
                retorno += "QUATROCENTOS";
                break;
            case 5:
                retorno += "QUINHENTOS";
                break;
            case 6:
                retorno += "SEISCENTOS";
                break;
            case 7:
                retorno += "SETECENTOS";
                break;
            case 8:
                retorno += "OITOCENTOS";
                break;
            case 9:
                retorno += "NOVECENTOS";
                break;
        }

        fracao = parte / 10;
        parte = parte % 10;
        if (fracao != 0) {
            if (primeiro) {
                primeiro = false;
            } else {
                retorno += " E ";
            }
        }
        switch (fracao) {
            case 1:
                if (parte == 0) {
                    retorno += "DEZ";
                } else {
                    switch (parte) {
                        case 1:
                            retorno += "ONZE";
                            break;
                        case 2:
                            retorno += "DOZE";
                            break;
                        case 3:
                            retorno += "TREZE";
                            break;
                        case 4:
                            retorno += "QUATORZE";
                            break;
                        case 5:
                            retorno += "QUINZE";
                            break;
                        case 6:
                            retorno += "DEZESSEIS";
                            break;
                        case 7:
                            retorno += "DEZESSETE";
                            break;
                        case 8:
                            retorno += "DEZOITO";
                            break;
                        case 9:
                            retorno += "DEZENOVE";
                            break;
                    }
                }
                return retorno;
            case 2:
                retorno += "VINTE";
                break;
            case 3:
                retorno += "TRINTA";
                break;
            case 4:
                retorno += "QUARENTA";
                break;
            case 5:
                retorno += "CINQUENTA";
                break;
            case 6:
                retorno += "SESSENTA";
                break;
            case 7:
                retorno += "SETENTA";
                break;
            case 8:
                retorno += "OITENTA";
                break;
            case 9:
                retorno += "NOVENTA";
                break;
        }

        if (parte != 0) {
            if (primeiro) {
                primeiro = false;
            } else {
                retorno += " E ";
            }
        }

        switch (parte) {
            case 1:
                retorno += "UM";
                break;
            case 2:
                retorno += "DOIS";
                break;
            case 3:
                retorno += "TRES";
                break;
            case 4:
                retorno += "QUATRO";
                break;
            case 5:
                retorno += "CINCO";
                break;
            case 6:
                retorno += "SEIS";
                break;
            case 7:
                retorno += "SETE";
                break;
            case 8:
                retorno += "OITO";
                break;
            case 9:
                retorno += "NOVE";
                break;
        }
        return retorno;
    }
}
