package Containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;

public class MainContainer {
    public static void main(String[] args) throws ControllerException {
        Runtime run = Runtime.instance();
        ProfileImpl pr = new ProfileImpl();
        pr.setParameter(ProfileImpl.GUI, "true");
        AgentContainer runMainContainer = run.createMainContainer(pr);
        runMainContainer.start();
    }
}
