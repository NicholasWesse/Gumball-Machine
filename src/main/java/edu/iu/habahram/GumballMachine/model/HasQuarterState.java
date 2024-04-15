package edu.iu.habahram.GumballMachine.model;

public class HasQuarterState implements IState{
    IGumballMachine gumballMachine;
    public HasQuarterState(IGumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
    @Override
    public TransitionResult insertQuarter() {
        String message = "You already have a quarter inserted";
        boolean succeeded = false;
        int count = gumballMachine.getCount();
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), count);
    }
    @Override
    public TransitionResult ejectQuarter() {
        gumballMachine.changeTheStateTo(GumballMachineState.NO_QUARTER);
        String message = "You get back a quarter";
        boolean succeeded = true;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }
    @Override
    public TransitionResult turnCrank() {

        String message = "Processing Gumball";
        boolean succeeded = true;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }
    @Override
    public TransitionResult dispense() {

        String message = "Here is a gumball";
        boolean succeeded = true;
        gumballMachine.changeTheStateTo(GumballMachineState.NO_QUARTER);
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

    @Override
    public void refill(int c){
        gumballMachine.refill(c);
    }
    @Override
    public String getTheName() {
        return GumballMachineState.HAS_QUARTER.name();
    }
}
