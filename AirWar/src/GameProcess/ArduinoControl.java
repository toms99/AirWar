package GameProcess;

import static jssc.SerialPort.MASK_RXCHAR;
//import javafx.collections.FXCollections;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;
import jssc.SerialPortList;
import GameProcess.KeyInput;

public class ArduinoControl {


    static ArrayList<String> portList = new ArrayList<String>();
   // public static ObservableList<String> portList;
    static SerialPort arduinoPort = null;

    public  static void detectPort(){
        //portList = FXCollections.observableArrayList();
        String[] serialPortNames = SerialPortList.getPortNames();
        for(String name: serialPortNames){
            System.out.println(name);
            portList.add(name);
        }
    }

    public static boolean connectArduino(String port){

        System.out.println("ARDUINO CONECTADO SATISFACTORIAMENTE");

        boolean success = false;
        SerialPort serialPort = new SerialPort(port);
        try {
            serialPort.openPort();
            serialPort.setParams(
                    SerialPort.BAUDRATE_9600, //Velocidad de comunicación
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setEventsMask(MASK_RXCHAR);
            serialPort.addEventListener((SerialPortEvent serialPortEvent) -> {
                if(serialPortEvent.isRXCHAR()){
                    try {
                        String st = serialPort.readString(serialPortEvent.getEventValue()).toString();
                        while(true) {
                            if(st.contains("s")) {
                                System.out.println("DISPARO");
                            }else if(st.contains("r")) {
                            }
                            else if(st.contains("l")) {
                            }
                            break;
                        }
                    } catch (SerialPortException ex) {
                        Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            arduinoPort = serialPort;
            success = true;
        } catch (SerialPortException ex) {
            Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SerialPortException: " + ex.toString());
        }
        return success;
    }

    public void disconnectArduino(){
        System.out.println("ARDUINO DESCONECTADO");
        if(arduinoPort != null){
            try {
                arduinoPort.removeEventListener();
                if(arduinoPort.isOpened()){
                    arduinoPort.closePort();
                }
                arduinoPort = null;
            } catch (SerialPortException ex) {
                Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}