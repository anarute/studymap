package br.com.jcomputacao.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * 14/02/2010 14:08:48
 * @author Murilo
 */
public class NumberUtil {
    private static final NumberFormat decimalBanco = NumberFormat.getNumberInstance(Locale.US);
    private static final NumberFormat decimal;

    static {
        decimalBanco.setMaximumFractionDigits(2);
        decimalBanco.setMinimumFractionDigits(2);
        decimalBanco.setGroupingUsed(false);
        
        decimal = NumberFormat.getNumberInstance();
        decimal.setMaximumFractionDigits(2);
        decimal.setMinimumFractionDigits(2);
        decimal.setGroupingUsed(false);
    }

    public static int soma(int [] vetor){
        int soma = 0;
        for(int posicao=0;posicao<vetor.length;posicao++){
            soma += vetor[posicao];
        }
        return soma;
    }

    public static String decimalBanco(Double valor){
        if (valor == null) {
            return null;
        }
        return decimalBanco.format(valor);
    }
    
    public static String decimal(Double valor){
        if (valor == null) {
            return null;
        }
        return decimal.format(valor);
    }

    public static double getDouble(String text) {
        if(text==null || "".equals(text)) {
            return 0d;
        } else {
            text = text.replace(",", ".");
            double valor = 0d;
            try {
                valor = decimalBanco.parse(text).doubleValue();
            } catch(NumberFormatException ex) {
                ex.printStackTrace(System.err);
            } finally {
                return valor;
            }
        }
    }

    public static long getLong(String text) {
        if(text==null || "".equals(text)) {
            return 0;
        } else {
            text = text.replace(",", ".");
            long valor = 0;
            try {
                valor = Long.parseLong(text);
            } catch(NumberFormatException ex) {
                ex.printStackTrace(System.err);
            } finally {
                return valor;
            }
        }
    }
    
    public static Integer getInteger(String text) {
        if(text==null || "".equals(text)) {
            return 0;
        } else {
            text = text.replace(",", ".");
            int valor = 0;
            try {
                valor = Integer.parseInt(text);
            } catch(NumberFormatException ex) {
                ex.printStackTrace(System.err);
            } finally {
                return valor;
            }
        }
    }

    public static boolean isNullOrEmpty(Integer intt) {
        return (intt == null);
    }
    public static boolean isNullOrEmpty(boolean boleano) {
        return (boleano == false);
    }

    public static int calculaModuloOnze(String numero){
        Integer array[] = new Integer[numero.length()];
        for(int f = 0; f < numero.length(); f++){
            array[f] = Integer.parseInt(String.valueOf(numero.charAt(f)));
        }
        long soma = 0;
        int c = 2;
        for(int i = 1; i <= numero.length(); i++){
            if(c<7){
                soma = (array[i-1] * c);
                c++;
            }else {
                soma = (array[i-1] * c);
                c=2;
            }
        }
        if((soma%11) < 2){
            return 1;
        }else{
            return (int)(11-(soma%11));
        }
    }

    public static int getModulo11(String campo, int type) {
        int multiplicador = 2;
        int multiplicacao = 0;
        int soma_campo = 0;

        for (int i = campo.length(); i > 0; i--) {
            multiplicacao = Integer.parseInt(campo.substring(i - 1, i)) * multiplicador;

            soma_campo = soma_campo + multiplicacao;

            multiplicador++;
            if (multiplicador > 7 && type == 7) {
                multiplicador = 2;
            } else if (multiplicador > 9 && type == 9) {
                multiplicador = 2;
            }
        }

        int dac = 11 - (soma_campo % 11);

        if (dac > 9 && type == 7) {
            dac = 0;
        } else if ((dac == 0 || dac == 1 || dac > 9) && type == 9) {
            dac = 1;
        }

        return dac;
    }

    public static String getNullSafeForUI(Number number) {
        if (number == null) {
            return "";
        }
        
        if(number instanceof Float || number instanceof Double) {
            return decimal(number.doubleValue());
        } else {
            return Long.toString(number.longValue());
        }
    }
    
    public static double[] calculaValorParcelas(int numeroParcelas, double valorTotal) {
        double valores[] = new double[numeroParcelas];
        double valor = valorTotal / numeroParcelas;
        valor = Double.parseDouble(decimalBanco(valor));
        double falta = valorTotal - (valor * numeroParcelas);
        for (int i = 0; i < valores.length; i++) {
            if (i == 0) {
                valores[0] = valor + falta;
            } else {
                valores[i] = valor;
            }
        }
        return valores;
    }

}
