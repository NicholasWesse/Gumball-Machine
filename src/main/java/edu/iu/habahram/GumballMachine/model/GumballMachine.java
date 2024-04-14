package edu.iu.habahram.GumballMachine.model;

public class GumballMachine implements IGumballMachine {
    final String INSERT_QUARTER = Transition.INSERT_QUARTER.name();
    final String EJECT_QUARTER = Transition.EJECT_QUARTER.name();
    final String TURN_CRANK = Transition.TURN_CRANK.name();
    String id;
    IState soldOutState;
    IState noQuarterState;
    IState hasQuarterState;
    IState soldState;

    private String id;
    IState state = soldOutState;
    int count = 0;

    public GumballMachine(String id, IState state, int count) {
        this.id = id;
        this.state = state;
        this.count = count;
    }

    @Override
    public TransitionResult insertQuarter() {
        boolean succeeded = false;
        String message = "";
        if (state instanceof HasQuarterState) {
            message = "You can't insert another quarter";
        } else if (state instanceof NoQuarterState) {
            state = hasQuarterState;
            message = "You inserted a quarter";
            succeeded = true;
        } else if (state instanceof SoldOutState) {
            message = "You can't insert a quarter, the machine is sold out";
        } else if (state instanceof SoldState) {
            message = "Please wait, we're already giving you a gumball";
        }
        return new TransitionResult(succeeded, message, state.getTheName(), count);
    }

    @Override
    public TransitionResult ejectQuarter() {
        boolean succeeded = false;
        String message = "";
        if (state instanceof HasQuarterState) {
            state = noQuarterState;
            message = "Have your quarter";
            succeeded = true;
        } else if (state instanceof NoQuarterState) {
            message = "There is no quarter";
        } else if (state instanceof SoldOutState) {
            message = "There is no quarter or gumballs";
        } else if (state instanceof SoldState) {
            message = "You cant have it back after buying";
        }
        return new TransitionResult(succeeded, message, state.getTheName(), count);

    }

    @Override
    public TransitionResult turnCrank() {
        boolean succeeded = false;
        String message = "";
        if (state instanceof HasQuarterState) {
            message = "Readying Gumball";
            state = noQuarterState;
            succeeded = true;
        } else if (state instanceof NoQuarterState) {
            message = "You have no quarter";
        } else if (state instanceof SoldOutState) {
            message = "Sorry there are no gumballs to give";
        } else if (state instanceof SoldState) {
            message = "Please wait, we're already giving you a gumball";
        }
        return new TransitionResult(succeeded, message, state.getTheName(), count);

    }

    public TransitionResult dispense(){
        boolean succeeded = false;
        String message = "";
        if(turnCrank().succeeded()){
            message = "Here is the gumball";
            succeeded = true;
            count--;
        }
        else{
            message = "No gumball for you";
        }
        state = noQuarterState;

        return new TransitionResult(succeeded,message, state.getTheName(), count);

    }

    @Override
    public void changeTheStateTo(GumballMachineState name) {

    }

    @Override
    public Integer getCount() {
        return count;
    }

    @Override
    public String getTheStateName() {
        return null;
    }

    @Override
    public void releaseBall() {

    }


}
