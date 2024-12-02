package io.github.mung.dataprovider;

import io.github.mung.constants.FrameworkConstants;
import io.github.mung.helpers.ExcelHelpers;
import io.github.mung.helpers.SystemHelpers;
import org.testng.annotations.DataProvider;

public class DataProviderAddProduct {

    @DataProvider(name = "data_provider_add_product")
    public Object[][] dataAddProduct() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        Object[][] data = excelHelpers.getDataHashTable(SystemHelpers.getCurrentDir() + FrameworkConstants.EXCEL_CMS_DATA, "AddProduct", 2, 2);
        return data;
    }
}
