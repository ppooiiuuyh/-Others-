package data;

import java.awt.*;

/**
 * Created by ppooi on 2017-03-26.
 */
public class Layouts {
    public static final int MainFrame_LocationX = 0;
    public static final int MainFrame_LocationY = 0;/*
    public static final double MainFrame_SizeX = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final double MainFrame_SizeY = Toolkit.getDefaultToolkit().getScreenSize().height-30;
    */
    //public static final double MainFrame_SizeX = Toolkit.getDefaultToolkit().getScreenSize().width;
    //public static final double MainFrame_SizeY = Toolkit.getDefaultToolkit().getScreenSize().height;
   public static double MainFrame_SizeX = 480;
    public static  double MainFrame_SizeY = 320;
    public static  double MainFrame_PanelSizeX = MainFrame_SizeX;
    public static  double MainFrame_PanelSizeY = MainFrame_SizeY;



    public static final int MainTab_LocationX = 0;
    public static final int MainTab_LocationY = 0;
    public static final double MainTab_SizeX = MainFrame_PanelSizeX;
    public static final double MainTab_SizeY = MainFrame_PanelSizeY;
    public static final double MainTab_PanelSizeX = MainFrame_SizeX-5;
    public static final double MainTab_PanelSizeY = MainFrame_SizeY-30;


    /*MainTab_FirstPanel*/
    public static final int MainTab_FirstPanel_LocationX = 0;
    public static final int MainTab_FirstPanel_LocationY = 0;
    public static final double MainTab_FirstPanel_SizeX = MainTab_PanelSizeX/3*2;
    public static final double MainTab_FirstPanel_SizeY = MainTab_PanelSizeY;

    public static final int MainTab_FirstPanel_PanelLocationX = 5;
    public static final int MainTab_FirstPanel_PanelLocationY = 16;
    public static final double MainTab_FirstPanel_PanelSizeX = MainTab_FirstPanel_SizeX-10;
    public static final double MainTab_FirstPanel_PanelSizeY = MainTab_FirstPanel_SizeY-22;
    public static final Rectangle MainTab_FirstPanel_PanelBounds =
            new Rectangle(MainTab_FirstPanel_PanelLocationX,MainTab_FirstPanel_PanelLocationY,
                        (int)MainTab_FirstPanel_PanelSizeX,(int)MainTab_FirstPanel_PanelSizeY);

    /*Image*/
    public static final int FirePicture_LocationX = 0;
    public static final int FirePicture_LocationY = 0;
    public static final double FirePicture_SizeX = MainTab_FirstPanel_PanelSizeX;
    public static final double FirePicture_SizeY =  MainTab_FirstPanel_PanelSizeY;





    /*MainTab_SecondPanel*/
    public static final int MainTab_SecondPanel_LocationX = (int)MainTab_FirstPanel_SizeX;
    public static final int MainTab_SecondPanel_LocationY = 0;
    public static final double MainTab_SecondPanel_SizeX = MainTab_PanelSizeX/3*1;
    public static final double MainTab_SecondPanel_SizeY = MainTab_PanelSizeY/2;

    public static final int MainTab_SecondPanel_PanelLocationX = 5;
    public static final int MainTab_SecondPanel_PanelLocationY = 16;
    public static final double MainTab_SecondPanel_PanelSizeX = MainTab_SecondPanel_SizeX-10;
    public static final double MainTab_SecondPanel_PanelSizeY = MainTab_SecondPanel_SizeY-22;
    public static final Rectangle MainTab_SecondPanel_PanelBounds =
            new Rectangle(MainTab_SecondPanel_PanelLocationX,MainTab_SecondPanel_PanelLocationY,
                        (int)MainTab_SecondPanel_PanelSizeX,(int)MainTab_SecondPanel_PanelSizeY);

    /*MainTab_ThirdPanel*/
    public static final int MainTab_ThirdPanel_LocationX = (int)MainTab_FirstPanel_SizeX;
    public static final int MainTab_ThirdPanel_LocationY = (int)MainTab_PanelSizeY/2;
    public static final double MainTab_ThirdPanel_SizeX = MainTab_PanelSizeX/3*1;
    public static final double MainTab_ThirdPanel_SizeY = MainTab_PanelSizeY/2;

    public static final int MainTab_ThirdPanel_PanelLocationX = 5;
    public static final int MainTab_ThirdPanel_PanelLocationY = 16;
    public static final double MainTab_ThirdPanel_PanelSizeX = MainTab_SecondPanel_SizeX-10;
    public static final double MainTab_ThirdPanel_PanelSizeY = MainTab_SecondPanel_SizeY-22;
    public static final Rectangle MainTab_ThirdPanel_PanelBounds =
            new Rectangle(MainTab_ThirdPanel_PanelLocationX,MainTab_ThirdPanel_PanelLocationY,
                    (int)MainTab_ThirdPanel_PanelSizeX,(int)MainTab_ThirdPanel_PanelSizeY);


















 /*MainTab_ConnectionCheck*/
    public static final int ConnectionCheck_LocationX = MainTab_SecondPanel_PanelLocationX;
    public static final int ConnectionCheck_LocationY = MainTab_SecondPanel_PanelLocationY;
    public static final double ConnectionCheck_SizeX = MainTab_SecondPanel_PanelSizeX;
    public static final double ConnectionCheck_SizeY = MainTab_SecondPanel_PanelSizeY;
    public static final Rectangle ConnectionCheck_Bounds =
            new Rectangle(ConnectionCheck_LocationX,ConnectionCheck_LocationY,
                    (int)ConnectionCheck_SizeX,(int)ConnectionCheck_SizeY);








    /*ConnectionTab*/
    public static final int ConnectionTab_LocationX = 0;
    public static final int ConnectionTab_LocationY = 0;
    public static final double ConnectionTab_SizeX = MainFrame_PanelSizeX;
    public static final double ConnectionTab_SizeY = MainFrame_PanelSizeY;
    public static final Rectangle ConnectionTab_Bounds =
            new Rectangle(ConnectionTab_LocationX,ConnectionTab_LocationY,
                    (int)ConnectionTab_SizeX,(int)ConnectionTab_SizeY);

    public static final int ConnectionTab_PanelLocationX = 0;
    public static final int ConnectionTab_PanelLocationY = 0;
    public static final double ConnectionTab_PanelSizeX = MainFrame_SizeX-5;
    public static final double ConnectionTab_PanelSizeY = MainFrame_SizeY-30;
    public static final Rectangle ConnectionTab_PanelBounds =
            new Rectangle(ConnectionTab_PanelLocationX,ConnectionTab_PanelLocationY,
                    (int)ConnectionTab_PanelSizeX,(int)ConnectionTab_PanelSizeY);

    /*ConnectionState*/
    public static final int ConnectionState_LocationX = ConnectionTab_PanelLocationX;
    public static final int ConnectionState_LocationY = ConnectionTab_PanelLocationY;
    public static final double ConnectionState_SizeX = ConnectionTab_PanelSizeX;
    public static final double ConnectionState_SizeY = ConnectionTab_PanelSizeY ;
    public static final Rectangle ConnectionState_Bounds =
            new Rectangle(ConnectionState_LocationX,ConnectionState_LocationY,
                    (int)ConnectionState_SizeX,(int)ConnectionState_SizeY);





    /*ModeTab*/
    public static final int ModeTab_LocationX = 0;
    public static final int ModeTab_LocationY = 0;
    public static final double ModeTab_SizeX = MainFrame_PanelSizeX;
    public static final double ModeTab_SizeY = MainFrame_PanelSizeY;
    public static final int ModeTab_PanelLocationX = 0;
    public static final int ModeTab_PanelLocationY = 0;
    public static final double ModeTab_PanelSizeX = MainFrame_SizeX-5;
    public static final double ModeTab_PanelSizeY = MainFrame_SizeY-30;
    public static final Rectangle ModeTab_PanelBounds =
            new Rectangle(ModeTab_PanelLocationX,ModeTab_PanelLocationY,
                        (int)ModeTab_PanelSizeX,(int)ModeTab_PanelSizeY);


    /*ModeTab_FirstPanel*/
    public static final int ModeTab_FirstPanel_LocationX = ModeTab_PanelLocationX;
    public static final int ModeTab_FirstPanel_LocationY = ModeTab_PanelLocationY;
    public static final double ModeTab_FirstPanel_SizeX = ModeTab_PanelSizeX;
    public static final double ModeTab_FirstPanel_SizeY = ModeTab_PanelSizeY;

    public static final int ModeTab_FirstPanel_PanelLocationX = 0;
    public static final int ModeTab_FirstPanel_PanelLocationY = 0;
    public static final double ModeTab_FirstPanel_PanelSizeX = ModeTab_FirstPanel_SizeX;
    public static final double ModeTab_FirstPanel_PanelSizeY = ModeTab_FirstPanel_SizeY;
    public static final Rectangle ModeTab_FirstPanel_PanelBounds =
            new Rectangle(ModeTab_FirstPanel_PanelLocationX,ModeTab_FirstPanel_PanelLocationY,
                        (int)ModeTab_FirstPanel_PanelSizeX,(int)ModeTab_FirstPanel_PanelSizeY);


    /*JPanel_ModeTab_APSlider*/
    public static final int ModeTab_APSlider_LocationX = ModeTab_FirstPanel_PanelLocationX;
    public static final int ModeTab_APSlider_LocationY = ModeTab_FirstPanel_PanelLocationY;
    public static final double ModeTab_APSlider_SizeX = ModeTab_FirstPanel_PanelSizeX;
    public static final double ModeTab_APSlider_SizeY = ModeTab_FirstPanel_PanelSizeY;
    public static final Rectangle ModeTab_APSlider_Bounds =
            new Rectangle(ModeTab_APSlider_LocationX,ModeTab_APSlider_LocationY,
                    (int)ModeTab_APSlider_SizeX,(int)ModeTab_APSlider_SizeY);

    /*Slider background*/
    public static final int SliderBG_LocationX = ModeTab_APSlider_LocationX;
    public static final int SliderBG_LocationY = ModeTab_APSlider_LocationY;
    public static final int SliderBG_SizeX = (int)ModeTab_APSlider_SizeX;
    public static final int SliderBG_SizeY = (int)ModeTab_APSlider_SizeY;

    /*Slider Knob*/
    public static final int SliderKnob_SizeX = (int)ModeTab_APSlider_SizeX/100*20;
    public static final int SliderKnob_SizeY = SliderKnob_SizeX;
    public static final int SliderKnob_LocationX_Default = SliderBG_SizeX/2 - SliderKnob_SizeX/2;
    public static final int SliderKnob_LocationY_Default = SliderBG_SizeY/2 - SliderKnob_SizeY/2;

    public static final int SliderKnob_PointX_AP = (int)(((double)SliderBG_SizeX)/100*32);
    public static final int SliderKnob_PointY_AP = SliderBG_SizeY/2;
    public static final int SliderKnob_LocationX_AP = SliderKnob_PointX_AP - SliderKnob_SizeX/2;
    public static final int SliderKnob_LocationY_AP = SliderKnob_PointY_AP - SliderKnob_SizeY/2;

    public static final int SliderKnob_PointX_Host = (int)(((double)SliderBG_SizeX)/100*68);
    public static final int SliderKnob_PointY_Host = SliderBG_SizeY/2;
    public static final int SliderKnob_LocationX_Host = SliderKnob_PointX_Host - SliderKnob_SizeX/2;
    public static final int SliderKnob_LocationY_Host = SliderKnob_PointY_Host - SliderKnob_SizeY/2;







    /*TestTab*/
    public static final int TestTab_LocationX = 0;
    public static final int TestTab_LocationY = 0;
    public static final double TestTab_SizeX = MainFrame_PanelSizeX;
    public static final double TestTab_SizeY = MainFrame_PanelSizeY;

    public static final int TestTab_PanelLocationX = 0;
    public static final int TestTab_PanelLocationY = 0;
    public static final double TestTab_PanelSizeX = MainFrame_SizeX-5;
    public static final double TestTab_PanelSizeY = MainFrame_SizeY-30;
    public static final Rectangle TestTabTab_PanelBounds =
            new Rectangle(TestTab_PanelLocationX,TestTab_PanelLocationY,
                    (int)TestTab_PanelSizeX,(int)TestTab_PanelSizeY);



    /*TestTab_FirstPanel*/
    public static final int TestTab_FirstPanel_LocationX = 0;
    public static final int TestTab_FirstPanel_LocationY = 0;
    public static final double TestTab_FirstPanel_SizeX = TestTab_PanelSizeX;
    public static final double TestTab_FirstPanel_SizeY = TestTab_PanelSizeY/5;
    public static final Rectangle TestTab_FirstPanel_Bounds =
            new Rectangle(TestTab_FirstPanel_LocationX,TestTab_FirstPanel_LocationY,
                    (int)TestTab_FirstPanel_SizeX,(int)TestTab_FirstPanel_SizeY);

    public static final int TestTab_FirstPanel_PanelLocationX = 5;
    public static final int TestTab_FirstPanel_PanelLocationY = 16;
    public static final double TestTab_FirstPanel_PanelSizeX = TestTab_FirstPanel_SizeX-10;
    public static final double TestTab_FirstPanel_PanelSizeY = TestTab_FirstPanel_SizeY-22;
    public static final Rectangle TestTab_FirstPanel_PanelBounds =
            new Rectangle(TestTab_FirstPanel_PanelLocationX,TestTab_FirstPanel_PanelLocationY,
                    (int)TestTab_FirstPanel_PanelSizeX,(int)TestTab_FirstPanel_PanelSizeY);

    /*Sounde Test*/
    public static final int SoundTest_LocationX = TestTab_FirstPanel_PanelLocationX;
    public static final int SoundTest_LocationY = TestTab_FirstPanel_PanelLocationY;
    public static final double SoundTest_SizeX = TestTab_FirstPanel_PanelSizeX;
    public static final double SoundTest_SizeY = TestTab_FirstPanel_PanelSizeY;
    public static final Rectangle SoundTest_Bounds =
            new Rectangle(SoundTest_LocationX,SoundTest_LocationY,
                    (int)SoundTest_SizeX,(int)SoundTest_SizeY);


}
