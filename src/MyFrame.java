import javax.swing.*;

public class MyFrame extends JFrame{
    MyPanel mypanel;

    public MyFrame(){
        super();
        mypanel = new MyPanel();
        this.add(mypanel);
    }


}