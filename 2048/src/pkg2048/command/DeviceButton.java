package pkg2048.command;

import pkg2048.command.Command;

public class DeviceButton {
    Command command;
    
    public DeviceButton(Command newCommand){
        command = newCommand;
    }
    
    public void press(){
        command.execute();
    }
}
