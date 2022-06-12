package Containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class ServerContainer {
    public static void main(String[] args) throws ControllerException {
        Runtime run = Runtime.instance();
        ProfileImpl pf = new ProfileImpl();
        pf.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        AgentContainer runAgentContainer = run.createAgentContainer(pf);
        AgentController agentController = runAgentContainer.createNewAgent("Server", "Agents.Server", new Object[]{});
        agentController.start();
    }
}
