package TicTacToeGameProject;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
class TicTacToe extends JFrame implements ItemListener, ActionListener{
    int i,j,ii,jj,x,y,yesNull;
    int[][] a ={{10,1,2,3,11},{10,1,4,7,11},{10,1,5,9,11},{10,2,5,8,11},
            {10,3,5,7,11},{10,3,6,9,11},{10,4,5,6,11},
            {10,7,8,9,11} };
    int[][] a1 ={{10,1,2,3,11},{10,1,4,7,11},{10,1,5,9,11},{10,2,5,8,11},
            {10,3,5,7,11},{10,3,6,9,11},{10,4,5,6,11},{10,7,8,9,11} };

    boolean state,type,set;

    Icon ic1,ic2,icon,ic11,ic22;
    Checkbox c1,c2;
    JButton[] b =new JButton[9];
    JButton reset;

    public void showButton(){

        x=10; y=10;j=0;
        for(i=0;i<=8;i++,x+=100,j++){
            b[i]=new JButton();
            if(j==3)
            {j=0; y+=100; x=10;}
            b[i].setBounds(x,y,100,100);
            add(b[i]);
            b[i].addActionListener(this);
        }
        reset=new JButton("RESET");
        reset.setBounds(100,350,100,50);
        add(reset);
        reset.addActionListener(this);
    }
    public  void check(int num1){
        for(ii=0;ii<=7;ii++){
            for(jj=1;jj<=3;jj++){
                if(a[ii][jj]==num1){
                    a[ii][4]=11;
                }
            }
        }
    }

    public void complicit(int num){

        for(i=0;i<=7;i++){
            for(j=1;j<=3;j++){
                if(a[i][j]==num){  a[i][0]=11; a[i][4]=10;    }
            }
        }
        for(i=0;i<=7;i++){              
            set=true;
            if(a[i][4]==10){              
                int count=0;
                for(j=1;j<=3;j++){        
                    if(b[(a[i][j]-1)].getIcon()!=null){ 
                        count++;
                    }                        
                    else{
                        yesNull=a[i][j]; 
                    }
                }                              
                if(count==2){                   
                    b[yesNull-1].setIcon(ic2);
                    this.check(yesNull); set=false;break;
                }                                 
            }                                    
            else
            if(a[i][0]==10){
                for(j=1;j<=3;j++){ 
                    if(b[(a[i][j]-1)].getIcon()==null){ 
                        b[(a[i][j]-1)].setIcon(ic2);
                        this.check(a[i][j]);
                        set=false;
                        break;
                    }
                }
                if(!set)
                    break;
            }
            if(!set) {
                break;
            }
        }
    }

    TicTacToe() throws IOException {
        super("tic tac toe by Kishan");

        CheckboxGroup cbg=new CheckboxGroup();
        c1=new Checkbox("vs computer",cbg,false);
        c2=new Checkbox("vs friend",cbg,false);
        c1.setBounds(120,80,100,40);
        c2.setBounds(120,150,100,40);
        add(c1); add(c2);
        c1.addItemListener(this);
        c2.addItemListener(this);


        state=true;type=true;set=true;
        String path1 = "https://static.thenounproject.com/png/875803-200.png";
        String path2 = "https://static.thenounproject.com/png/2200479-200.png";
        String path3 = "https://img.freepik.com/free-vector/cute-chicken-dabbing-pose-cartoon-vector-icon-illustration-animal-nature-icon-concept-isolated-flat_138676-5521.jpg?auto=format&h=200";
        String path4 = "https://img.freepik.com/free-vector/cute-cool-baby-holding-teddy-bear-doll-cartoon-vector-icon-illustration-people-holiday-isolated_138676-5356.jpg?auto=format&h=200";

        URL url1 = new URL(path1);
        URL url2 = new URL(path2);
        URL url3 = new URL(path3);
        URL url4 = new URL(path4);
        BufferedImage img1 = ImageIO.read(url1);
        BufferedImage img2 = ImageIO.read(url2);
        BufferedImage img3 = ImageIO.read(url3);
        BufferedImage img4 = ImageIO.read(url4);
        ic1=new ImageIcon(img1);
        ic2=new ImageIcon(img2);
        ic11=new ImageIcon(img3);
        ic22=new ImageIcon(img4);

        setLayout(null);
        setSize(330,450);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void itemStateChanged(ItemEvent e){
        if(c1.getState())
        {
            type=false;
        }

        else if(c2.getState()){
            type=true;
        }
        remove(c1);remove(c2);
        repaint(0,0,330,450);
        showButton();
    }
    public void actionPerformed(ActionEvent e){
        if(type){
            if(e.getSource()==reset){
                for(i=0;i<=8;i++){
                    b[i].setIcon(null);
                }
            }else{
                for(i=0;i<=8;i++){
                    if(e.getSource()==b[i]){

                        if(b[i].getIcon()==null){
                            if(state){ icon=ic2;
                                state=false;} else{ icon=ic1; state=true; }
                            b[i].setIcon(icon);
                        }
                    }
                }
            }
        }else {
            if(e.getSource()==reset){
                for(i=0;i<=8;i++){
                    b[i].setIcon(null);
                }
                for(i=0;i<=7;i++)
                    for(j=0;j<=4;j++)
                        a[i][j]=a1[i][j];
            }else{
                for(i=0;i<=8;i++){
                    if(e.getSource()==b[i]){
                        if(b[i].getIcon()==null){
                            b[i].setIcon(ic1);
                            if(b[4].getIcon()==null){
                                b[4].setIcon(ic2);
                                this.check(5);
                            } else{
                                this.complicit(i);
                            }
                        }
                    }
                }
            }
        }

        for(i=0;i<=7;i++){

            Icon icon1=b[(a[i][1]-1)].getIcon();
            Icon icon2=b[(a[i][2]-1)].getIcon();
            Icon icon3=b[(a[i][3]-1)].getIcon();
            if((icon1==icon2)&&(icon2==icon3)&&(icon1!=null)){
                if(icon1==ic1){
                    b[(a[i][1]-1)].setIcon(ic11);
                    b[(a[i][2]-1)].setIcon(ic11);
                    b[(a[i][3]-1)].setIcon(ic11);
                    JOptionPane.showMessageDialog(TicTacToe.this,"!!!YOU won!!! click reset");
                    break;
                }else if(icon1==ic2){
                    b[(a[i][1]-1)].setIcon(ic22);
                    b[(a[i][2]-1)].setIcon(ic22);
                    b[(a[i][3]-1)].setIcon(ic22);
                    JOptionPane.showMessageDialog(TicTacToe.this,"won! click reset");
                    break;
                }
            }
        }
    }
  public static void main(String []args) throws IOException {
        new TicTacToe();
    }
}
