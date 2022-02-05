import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<EratosphenElement> EratosphenList;
    static List<EratosphenElement> EratosphenSimple;
    static int mul = 0;
    final static int sizeBlock = 1000;
    public static void main(String[] args) {

        EratosphenList = new ArrayList<>();
        EratosphenSimple = new ArrayList<>();
        EratosphenList.add(new EratosphenElement(0, false));
        EratosphenList.add(new EratosphenElement(0, false));

        Scanner in = new Scanner(System.in);
        int numberSimple = in.nextInt(); //Чтение номера простого числа, которое нужно найти
        while (EratosphenSimple.size() < numberSimple){
            addElemToEratosphenList();     //Заполнение массива с числами
            EratosphenSimple.clear();
            for (int k = mul>1? 1 : 2; k*k<=sizeBlock*mul; k++){
                if(EratosphenList.get(k).isState())
                {
                    for(int l = k*k;l <= sizeBlock*mul; l+=k)
                    {
                        EratosphenList.get(l).setState(false);
                    }
                }
            }

            for (EratosphenElement el: EratosphenList) {
                if(el.isState())
                    EratosphenSimple.add(el);
            }
        }
        System.out.println(EratosphenSimple.get(numberSimple-1).getNum());
        //long time = System.currentTimeMillis() - startTime;
        //System.out.println(time);

    }
    public static void addElemToEratosphenList() {
        for(int i = mul > 0? 1 : 2; i < sizeBlock+1; ++i)  //Если mul = 0, значит это первое создание списка, поэтому добавляем со второго элемента. Если больше 1, то с нулевого
        {
            EratosphenList.add(new EratosphenElement(i + sizeBlock*mul, true));
        }
        mul++;
    }
}



class EratosphenElement {
    private int num;
    private boolean state;

    public EratosphenElement(int num, boolean state) {
        this.num = num;
        this.state = state;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
