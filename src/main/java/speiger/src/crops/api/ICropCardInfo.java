package speiger.src.crops.api;

import java.util.List;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Speiger
 * Class to add Informations from CropCards.
 * This has Priorty over the ICropInfo
 * @requirement: The class that implement this class need to extends CropCard
 */
public interface ICropCardInfo {
    /**
     * @return a list of "Important" Information,
     * which are needed to grow the Crop.
     * Like: a Special Light Level or something.
     */
    public List<String> getCropInformation();

    /**
     * @return the DisplayItem for the CropCard he is checking.
     * null means it get not implemented. So you can return null
     */
    public ItemStack getDisplayItem();
}
