package com.github.retrooper.packetevents.protocol.world.states;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.manager.server.ServerVersion;
import com.github.retrooper.packetevents.protocol.world.states.enums.*;
import com.github.retrooper.packetevents.protocol.world.states.type.StateType;
import com.github.retrooper.packetevents.protocol.world.states.type.StateTypes;
import com.github.retrooper.packetevents.protocol.world.states.type.StateValue;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * This class is designed to take advantage of modern minecraft versions
 * It has also been designed so that legacy versions can use this system
 * <p>
 * Write your code once, and use it everywhere.  Platform and version agnostic.
 * <p>
 * The mappings for legacy versions (1.12) was generated by setting blocks in the world at the pos id * 2, 255, data * 2
 * and then the world was upgraded to 1.18 and the block was read, dumping it all into a text file.
 * <p>
 * Mappings from modern versions are from ViaVersion, who have a similar (but a bit slower) system.
 */
public class WrappedBlockState {
    int globalID;
    StateType type;
    EnumMap<StateValue, Object> data = new EnumMap<>(StateValue.class);

    private static final WrappedBlockState AIR = new WrappedBlockState(StateTypes.AIR, new EnumMap<>(StateValue.class), 0);

    private static final HashMap<String, WrappedBlockState> BY_STRING = new HashMap<>();
    private static final HashMap<Integer, WrappedBlockState> BY_ID = new HashMap<>();
    private static final HashMap<WrappedBlockState, String> INTO_STRING = new HashMap<>();
    private static final HashMap<WrappedBlockState, Integer> INTO_ID = new HashMap<>();

    public WrappedBlockState(StateType type, String[] data, int globalID) {
        this.type = type;
        this.globalID = globalID;
        if (data == null) return;
        for (String s : data) {
            String[] split = s.split("=");
            StateValue value = StateValue.byName(split[0]);
            this.data.put(value, value.getParser().apply(split[1].toUpperCase(Locale.ROOT)));
        }
    }

    public WrappedBlockState(StateType type, EnumMap<StateValue, Object> data, int globalID) {
        this.globalID = globalID;
        this.type = type;
        this.data = data;
    }

    @Override
    public WrappedBlockState clone() {
        return new WrappedBlockState(type, data, globalID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WrappedBlockState that = (WrappedBlockState) o;
        return globalID == that.globalID;
    }

    @Override
    public int hashCode() {
        return globalID;
    }

    // Begin all block data types
    public int getAge() {
        return (int) data.get(StateValue.AGE);
    }

    public void setAge(int age) {
        data.put(StateValue.AGE, age);
        checkIsStillValid();
    }

    public boolean getAttached() {
        return (boolean) data.get(StateValue.ATTACHED);
    }

    public void setAttached(boolean attached) {
        data.put(StateValue.ATTACHED, attached);
        checkIsStillValid();
    }

    public Axis getAxis() {
        return (Axis) data.get(StateValue.AXIS);
    }

    public void setAxis(Axis axis) {
        data.put(StateValue.AXIS, axis);
        checkIsStillValid();
    }

    public boolean getBerries() {
        return (boolean) data.get(StateValue.BERRIES);
    }

    public void setBerries(boolean berries) {
        data.put(StateValue.BERRIES, berries);
        checkIsStillValid();
    }

    public int getBites() {
        return (int) data.get(StateValue.BITES);
    }

    public void setBites(int bites) {
        data.put(StateValue.BITES, bites);
        checkIsStillValid();
    }

    public boolean getBottom() {
        return (boolean) data.get(StateValue.BOTTOM);
    }

    public void setBottom(boolean bottom) {
        data.put(StateValue.BOTTOM, bottom);
        checkIsStillValid();
    }

    public int getCandles() {
        return (int) data.get(StateValue.CANDLES);
    }

    public void setCandles(int candles) {
        data.put(StateValue.CANDLES, candles);
        checkIsStillValid();
    }

    public int getCharges() {
        return (int) data.get(StateValue.CHARGES);
    }

    public void setCharges(int charges) {
        data.put(StateValue.CHARGES, charges);
        checkIsStillValid();
    }

    public boolean getConditional() {
        return (boolean) data.get(StateValue.CONDITIONAL);
    }

    public void setConditional(boolean conditional) {
        data.put(StateValue.CONDITIONAL, conditional);
        checkIsStillValid();
    }

    public int getDelay() {
        return (int) data.get(StateValue.DELAY);
    }

    public void setDelay(int delay) {
        data.put(StateValue.DELAY, delay);
        checkIsStillValid();
    }

    public boolean getDisarmed() {
        return (boolean) data.get(StateValue.DISARMED);
    }

    public void setDisarmed(boolean disarmed) {
        data.put(StateValue.DISARMED, disarmed);
        checkIsStillValid();
    }

    public int getDistance() {
        return (int) data.get(StateValue.DISTANCE);
    }

    public void setDistance(int distance) {
        data.put(StateValue.DISTANCE, distance);
        checkIsStillValid();
    }

    public boolean getDown() {
        return (boolean) data.get(StateValue.DOWN);
    }

    public void setDown(boolean down) {
        data.put(StateValue.DOWN, down);
        checkIsStillValid();
    }

    public boolean getDrag() {
        return (boolean) data.get(StateValue.DRAG);
    }

    public void setDrag(boolean drag) {
        data.put(StateValue.DRAG, drag);
        checkIsStillValid();
    }

    public int getEggs() {
        return (int) data.get(StateValue.EGGS);
    }

    public void setEggs(int eggs) {
        data.put(StateValue.EGGS, eggs);
        checkIsStillValid();
    }

    public boolean isEnabled() {
        return (boolean) data.get(StateValue.ENABLED);
    }

    public void setEnabled(boolean enabled) {
        data.put(StateValue.ENABLED, enabled);
        checkIsStillValid();
    }

    public boolean isExtended() {
        return (boolean) data.get(StateValue.EXTENDED);
    }

    public void setExtended(boolean extended) {
        data.put(StateValue.EXTENDED, extended);
        checkIsStillValid();
    }

    public boolean isEye() {
        return (boolean) data.get(StateValue.EYE);
    }

    public void setEye(boolean eye) {
        data.put(StateValue.EYE, eye);
        checkIsStillValid();
    }

    public Face getFace() {
        return (Face) data.get(StateValue.FACE);
    }

    public void setFace(Face face) {
        data.put(StateValue.FACE, face);
        checkIsStillValid();
    }

    public Facing getFacing() {
        return (Facing) data.get(StateValue.FACING);
    }

    public void setFacing(Facing facing) {
        data.put(StateValue.FACING, facing);
        checkIsStillValid();
    }

    public Half getHalf() {
        return (Half) data.get(StateValue.HALF);
    }

    public void setHalf(Half half) {
        data.put(StateValue.HALF, half);
        checkIsStillValid();
    }

    public boolean getHanging() {
        return (boolean) data.get(StateValue.HANGING);
    }

    public void setHanging(boolean hanging) {
        data.put(StateValue.HANGING, hanging);
        checkIsStillValid();
    }

    public boolean getHasBook() {
        return (boolean) data.get(StateValue.HAS_BOOK);
    }

    public void setHasBook(boolean hasBook) {
        data.put(StateValue.HAS_BOOK, hasBook);
        checkIsStillValid();
    }

    public boolean getHasBottle0() {
        return (boolean) data.get(StateValue.HAS_BOTTLE_0);
    }

    public void setHasBottle0(boolean hasBottle0) {
        data.put(StateValue.HAS_BOTTLE_0, hasBottle0);
        checkIsStillValid();
    }

    public boolean getHasBottle1() {
        return (boolean) data.get(StateValue.HAS_BOTTLE_1);
    }

    public void setHasBottle1(boolean hasBottle1) {
        data.put(StateValue.HAS_BOTTLE_1, hasBottle1);
        checkIsStillValid();
    }

    public boolean getHasBottle2() {
        return (boolean) data.get(StateValue.HAS_BOTTLE_2);
    }

    public void setHasBottle2(boolean hasBottle2) {
        data.put(StateValue.HAS_BOTTLE_2, hasBottle2);
        checkIsStillValid();
    }

    public boolean getHasRecord() {
        return (boolean) data.get(StateValue.HAS_RECORD);
    }

    public void setHasRecord(boolean hasRecord) {
        data.put(StateValue.HAS_RECORD, hasRecord);
        checkIsStillValid();
    }

    public int getHatch() {
        return (int) data.get(StateValue.HATCH);
    }

    public void setHatch(int hatch) {
        data.put(StateValue.HATCH, hatch);
        checkIsStillValid();
    }

    public Hinge getHinge() {
        return (Hinge) data.get(StateValue.HINGE);
    }

    public void setHinge(Hinge hinge) {
        data.put(StateValue.HINGE, hinge);
        checkIsStillValid();
    }

    public int getHoneyLevel() {
        return (int) data.get(StateValue.HONEY_LEVEL);
    }

    public void setHoneyLevel(int honeyLevel) {
        data.put(StateValue.HONEY_LEVEL, honeyLevel);
        checkIsStillValid();
    }

    public boolean inWall() {
        return (boolean) data.get(StateValue.IN_WALL);
    }

    public void setInWall(boolean inWall) {
        data.put(StateValue.IN_WALL, inWall);
        checkIsStillValid();
    }

    public Instrument getInstrument() {
        return (Instrument) data.get(StateValue.INSTRUMENT);
    }

    public void setInstrument(Instrument instrument) {
        data.put(StateValue.INSTRUMENT, instrument);
        checkIsStillValid();
    }

    public boolean isInverted() {
        return (boolean) data.get(StateValue.INVERTED);
    }

    public void setInverted(boolean inverted) {
        data.put(StateValue.INVERTED, inverted);
        checkIsStillValid();
    }

    public int getLayers() {
        return (int) data.get(StateValue.LAYERS);
    }

    public void setLayers(int layers) {
        data.put(StateValue.LAYERS, layers);
        checkIsStillValid();
    }

    public Leaves getLeaves() {
        return (Leaves) data.get(StateValue.LEAVES);
    }

    public void setLeaves(Leaves leaves) {
        data.put(StateValue.LEAVES, leaves);
        checkIsStillValid();
    }

    public int getLevel() {
        return (int) data.get(StateValue.LEVEL);
    }

    public void setLevel(int level) {
        data.put(StateValue.LEVEL, level);
        checkIsStillValid();
    }

    public boolean getLit() {
        return (boolean) data.get(StateValue.LIT);
    }

    public void setLit(boolean lit) {
        data.put(StateValue.LIT, lit);
        checkIsStillValid();
    }

    public boolean getLocked() {
        return (boolean) data.get(StateValue.LOCKED);
    }

    public void setLocked(boolean locked) {
        data.put(StateValue.LOCKED, locked);
        checkIsStillValid();
    }

    public Mode getMode() {
        return (Mode) data.get(StateValue.MODE);
    }

    public void setMode(Mode mode) {
        data.put(StateValue.MODE, mode);
        checkIsStillValid();
    }

    public int getMoisture() {
        return (int) data.get(StateValue.MOISTURE);
    }

    public void setMoisture(int moisture) {
        data.put(StateValue.MOISTURE, moisture);
        checkIsStillValid();
    }

    public North getNorth() {
        return (North) data.get(StateValue.NORTH);
    }

    public void setNorth(North north) {
        data.put(StateValue.NORTH, north);
        checkIsStillValid();
    }

    public int getNote() {
        return (int) data.get(StateValue.NOTE);
    }

    public void setNote(int note) {
        data.put(StateValue.NOTE, note);
        checkIsStillValid();
    }

    public boolean getOccupied() {
        return (boolean) data.get(StateValue.OCCUPIED);
    }

    public void setOccupied(boolean occupied) {
        data.put(StateValue.OCCUPIED, occupied);
        checkIsStillValid();
    }

    public boolean getOpen() {
        return (boolean) data.get(StateValue.OPEN);
    }

    public void setOpen(boolean open) {
        data.put(StateValue.OPEN, open);
        checkIsStillValid();
    }

    public Orientation getOrientation() {
        return (Orientation) data.get(StateValue.ORIENTATION);
    }

    public void setOrientation(Orientation orientation) {
        data.put(StateValue.ORIENTATION, orientation);
        checkIsStillValid();
    }

    public Part getPart() {
        return (Part) data.get(StateValue.PART);
    }

    public void setPart(Part part) {
        data.put(StateValue.PART, part);
        checkIsStillValid();
    }

    public boolean getPersistent() {
        return (boolean) data.get(StateValue.PERSISTENT);
    }

    public void setPersistent(boolean persistent) {
        data.put(StateValue.PERSISTENT, persistent);
        checkIsStillValid();
    }

    public int getPickles() {
        return (int) data.get(StateValue.PICKLES);
    }

    public void setPickles(int pickles) {
        data.put(StateValue.PICKLES, pickles);
        checkIsStillValid();
    }

    public int getPower() {
        return (int) data.get(StateValue.POWER);
    }

    public void setPower(int power) {
        data.put(StateValue.POWER, power);
        checkIsStillValid();
    }

    public boolean getPowered() {
        return (boolean) data.get(StateValue.POWERED);
    }

    public void setPowered(boolean powered) {
        data.put(StateValue.POWERED, powered);
        checkIsStillValid();
    }

    public int getRotation() {
        return (int) data.get(StateValue.ROTATION);
    }

    public void setRotation(int rotation) {
        data.put(StateValue.ROTATION, rotation);
        checkIsStillValid();
    }

    public SculkSensorPhase getSculkSensorPhase() {
        return (SculkSensorPhase) data.get(StateValue.SCULK_SENSOR_PHASE);
    }

    public void setSculkSensorPhase(SculkSensorPhase sculkSensorPhase) {
        data.put(StateValue.SCULK_SENSOR_PHASE, sculkSensorPhase);
        checkIsStillValid();
    }

    public Shape getShape() {
        return (Shape) data.get(StateValue.SHAPE);
    }

    public void setShape(Shape shape) {
        data.put(StateValue.SHAPE, shape);
        checkIsStillValid();
    }

    public boolean getShort() {
        return (boolean) data.get(StateValue.SHORT);
    }

    public void setShort(boolean short_) {
        data.put(StateValue.SHORT, short_);
        checkIsStillValid();
    }

    public boolean getSignalFire() {
        return (boolean) data.get(StateValue.SIGNAL_FIRE);
    }

    public void setSignalFire(boolean signalFire) {
        data.put(StateValue.SIGNAL_FIRE, signalFire);
        checkIsStillValid();
    }

    public boolean getSnowy() {
        return (boolean) data.get(StateValue.SNOWY);
    }

    public void setSnowy(boolean snowy) {
        data.put(StateValue.SNOWY, snowy);
        checkIsStillValid();
    }

    public int getStage() {
        return (int) data.get(StateValue.STAGE);
    }

    public void setStage(int stage) {
        data.put(StateValue.STAGE, stage);
        checkIsStillValid();
    }

    public South getSouth() {
        return (South) data.get(StateValue.SOUTH);
    }

    public void setSouth(South south) {
        data.put(StateValue.SOUTH, south);
        checkIsStillValid();
    }

    public Thickness getThickness() {
        return (Thickness) data.get(StateValue.THICKNESS);
    }

    public void setThickness(Thickness thickness) {
        data.put(StateValue.THICKNESS, thickness);
        checkIsStillValid();
    }

    public Tilt getTilt() {
        return (Tilt) data.get(StateValue.TILT);
    }

    public void setTilt(Tilt tilt) {
        data.put(StateValue.TILT, tilt);
        checkIsStillValid();
    }

    public boolean getTriggered() {
        return (boolean) data.get(StateValue.TRIGGERED);
    }

    public void setTriggered(boolean triggered) {
        data.put(StateValue.TRIGGERED, triggered);
        checkIsStillValid();
    }

    public Type getType() {
        return (Type) data.get(StateValue.TYPE);
    }

    public void setType(Type type) {
        data.put(StateValue.TYPE, type);
        checkIsStillValid();
    }

    public boolean getUnstable() {
        return (boolean) data.get(StateValue.UNSTABLE);
    }

    public void setUnstable(boolean unstable) {
        data.put(StateValue.UNSTABLE, unstable);
        checkIsStillValid();
    }

    public boolean getUp() {
        return (boolean) data.get(StateValue.UP);
    }

    public void setUp(boolean up) {
        data.put(StateValue.UP, up);
        checkIsStillValid();
    }

    public boolean getVerticalDirection() {
        return (boolean) data.get(StateValue.VERTICAL_DIRECTION);
    }

    public void setVerticalDirection(boolean verticalDirection) {
        data.put(StateValue.VERTICAL_DIRECTION, verticalDirection);
        checkIsStillValid();
    }

    public boolean getWaterlogged() {
        return (boolean) data.get(StateValue.WATERLOGGED);
    }

    public void setWaterlogged(boolean waterlogged) {
        data.put(StateValue.WATERLOGGED, waterlogged);
        checkIsStillValid();
    }

    public West getWest() {
        return (West) data.get(StateValue.WEST);
    }

    public void setWest(West west) {
        data.put(StateValue.WEST, west);
        checkIsStillValid();
    }

    /**
     * This checks if the block state is still valid after modifying it
     * If it isn't, then this block will be reverted to the previous state using the global id
     * This is because I believe it's better to revert illegal modification than to simply set to air for doing so
     * As multi-version makes block data still annoying
     */
    public void checkIsStillValid() {
        int oldGlobalID = globalID;
        globalID = getGlobalIDNoCache();
        if(type != StateTypes.AIR && globalID == 0) {
            WrappedBlockState blockState = getByGlobalId(oldGlobalID);
            this.type = blockState.type;
            this.data = blockState.data;
            this.globalID = blockState.globalID;

            // Stack tracing is expensive
            if (PacketEvents.getAPI().getSettings().isDebugEnabled()) {
                PacketEvents.getAPI().getLogManager().warn("Attempt to modify an unknown property for this game version and block!");;
                PacketEvents.getAPI().getLogManager().warn("Block: " + type.getName());
                for (Map.Entry<StateValue, Object> entry : data.entrySet()) {
                    PacketEvents.getAPI().getLogManager().warn(entry.getKey() + ": " + entry.getValue());
                }
                new IllegalStateException("An invalid modification was made to a block!").printStackTrace();
            }
        }
    }

    // End all block data types

    /**
     * Global ID
     * For pre-1.13: 4 bits of block data, 4 bits empty, 8 bits block type
     * For post-1.13: Global ID
     * @return
     */
    public int getGlobalId() {
        return globalID;
    }

    /**
     * Internal method for determining if the block state is still valid
     */
    private int getGlobalIDNoCache() {
        return INTO_ID.get(this);
    }

    @Override
    public String toString() {
        return INTO_STRING.get(this);
    }

    @NotNull
    public static WrappedBlockState getByGlobalId(int globalID) {
        return BY_ID.getOrDefault(globalID, AIR).clone();
    }

    @NotNull
    public static  WrappedBlockState getByString(String string) {
        return BY_STRING.getOrDefault(string, AIR).clone();
    }


    // TODO: 1.16.0/1.16.1 and 1.13.0/1.13.1 support
    private static String getMappingServerVersion(ServerVersion serverVersion) {
        if (serverVersion.isOlderThan(ServerVersion.V_1_13)) {
            return "legacy_block_mappings.txt";
        } else if (serverVersion.isOlderThan(ServerVersion.V_1_14)) {
            return "13.txt";
        } else if (serverVersion.isOlderThan(ServerVersion.V_1_15)) {
            return "14.txt";
        } else if (serverVersion.isOlderThan(ServerVersion.V_1_16)) {
            return "15.txt";
        } else if (serverVersion.isOlderThan(ServerVersion.V_1_16_2)) {
            return "16.txt";
        } else {
            return "17.txt";
        }
    }

    static {
        String mappingName = getMappingServerVersion(PacketEvents.getAPI().getServerManager().getVersion());
        InputStream mappings = WrappedBlockState.class.getClassLoader().getResourceAsStream("assets/mappings/block/" + mappingName);
        BufferedReader paletteReader = new BufferedReader(new InputStreamReader(mappings));

        if (PacketEvents.getAPI().getServerManager().getVersion().isOlderThan(ServerVersion.V_1_13)) {
            loadLegacy(paletteReader);
        } else {
            loadModern(paletteReader);
        }
    }

    private static void loadLegacy(BufferedReader reader) {
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                int id = Integer.parseInt(split[0]);
                int data = Integer.parseInt(split[1]);
                int combinedID = id + (data << 12);

                int endIndex = split[2].indexOf("[");

                String blockString = split[2].substring(0, endIndex != -1 ? endIndex : split[2].length());

                StateType type = StateTypes.getByName(blockString);

                String[] dataStrings = null;
                if (endIndex != -1) {
                    dataStrings = split[2].substring(endIndex + 1, line.length() - 1).split(",");
                }

                WrappedBlockState state = new WrappedBlockState(type, dataStrings, combinedID);

                BY_STRING.put(split[2], state);
                BY_ID.put(combinedID, state);
                INTO_STRING.put(state, split[2]);
                INTO_ID.put(state, combinedID);
            }
        } catch (IOException e) {
            PacketEvents.getAPI().getLogManager().debug("Palette reading failed! Unsupported version?");
            e.printStackTrace();
        }
    }

    private static void loadModern(BufferedReader reader) {
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                int afterID = line.indexOf(" ");
                int id = Integer.parseInt(line.substring(0, line.indexOf(" ")));

                String fullBlockString = line.substring(afterID + 1);
                int index = fullBlockString.indexOf("[");

                String blockString = fullBlockString.substring(0, index == -1 ? fullBlockString.length() : index);
                StateType type = StateTypes.getByName(blockString);

                String[] data = null;
                if (index != -1) {
                    data = fullBlockString.substring(index + 1, fullBlockString.length() - 1).split(",");
                }

                WrappedBlockState state = new WrappedBlockState(type, data, id);

                BY_STRING.put(fullBlockString, state);
                BY_ID.put(id, state);
                INTO_STRING.put(state, fullBlockString);
                INTO_ID.put(state, id);
            }
        } catch (IOException e) {
            PacketEvents.getAPI().getLogManager().debug("Palette reading failed! Unsupported version?");
            e.printStackTrace();
        }
    }
}