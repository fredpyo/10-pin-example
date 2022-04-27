package com.swnat;

public class Roll {
    String player;
    boolean isFoul;
    Integer pinsThrown;

    static Roll fromString(String rollAsString) {
        String[] x = rollAsString.split("\t");

        Roll c = new Roll();
        c.setPlayer(x[0]);
        if ("F".equals(x[1])) {
            c.setFoul(true);
        } else {
            c.setFoul(false);
            c.setPinsThrown(Integer.parseInt(x[1]));
        }
        return c;
    }

    void setPlayer(String playerName) {
        this.player = playerName;
    }

    String getPlayer() {
        return this.player;
    }

    void setFoul(boolean isFoul) {
        this.isFoul = isFoul;
        if (isFoul) this.pinsThrown = 0;
    }

    boolean isFoul() {
        return this.isFoul;
    }

    void setPinsThrown(Integer pinsThrown) {
        if (pinsThrown < 0 || pinsThrown > 10)
            throw new RuntimeException("Roll cannot be less than 0 or greater than 10");
        if (this.isFoul && pinsThrown != 0)
            throw new RuntimeException("Foul moves can't throw pins");

        this.pinsThrown = pinsThrown;
    }

    Integer getPinsThrown() {
        return this.isFoul ? 0 : this.pinsThrown;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Roll)) return false;
        Roll casted = (Roll) object;

        return this.player.equals(casted.player) && this.isFoul == casted.isFoul && this.pinsThrown == casted.pinsThrown;
    }

    public String toString() {
        return this.isFoul ? "F" : Integer.toString(this.pinsThrown);
    }
}
