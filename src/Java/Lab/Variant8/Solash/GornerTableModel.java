package Java.Lab.Variant8.Solash;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;

    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.setCoefficients(coefficients);
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    public int getColumnCount() { // В данной модели два столбца
        return 3;
    }

    public int getRowCount() { // Вычислить количество точек между началом и концом отрезка исходя из шага табулирования
        return new Double(Math.ceil((to - from) / step)).intValue() + 1;
    }

    public Object getValueAt(int row, int col) {
        double x = from + step * row; // Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ

        double result = 0.0;
        for (int i = 0; i < coefficients.length; i++) {
            result = result * x + coefficients[i];
        }

        boolean isFractionNotEven;
        double fractionalPart = (result - Math.floor(result))*1000;
        if(Math.abs(fractionalPart - Math.round(fractionalPart))<=0.1) fractionalPart = Math.round(fractionalPart);
        else fractionalPart = Math.floor(fractionalPart);
        if(fractionalPart != 0 && fractionalPart % 2 != 0) isFractionNotEven = true;
        else isFractionNotEven = false;



        switch(col) {
            case 0:
                return x;
            case 1:
                return result;
            case 2:
                return isFractionNotEven;
        }
        return 0;
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Значение X"; // Название 1-го столбца
            case 1:
                return "Значение многочлена"; // Название 2-го столбца
            case 2:
                return "«Дробная часть нечётная";
        }
        return null;
    }

    public Class<?> getColumnClass(int col) { // И в 1-ом и во 2-ом столбце находятся значения типа Double
        if (col == 2)
            return Boolean.class;
        else
            return Double.class;
    }

    public Double[] getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(Double[] coefficients) {
        this.coefficients = coefficients;
    }
}


