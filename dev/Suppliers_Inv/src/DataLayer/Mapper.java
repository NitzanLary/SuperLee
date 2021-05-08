package DataLayer;

import BussinessLayer.Inventory.*;
import BussinessLayer.ResponseT;
import BussinessLayer.Supplier.*;
import DataLayer.DAO.*;
import DataLayer.DTO.*;

import javax.xml.ws.Response;
import java.util.*;

public class Mapper {
    /**
     * This class holds the identity maps of Employees and Shifts.
     * Any access to the data base goes through here.
     */
    private BillOfQuantityDAO billOfQuantityDAO;
    private CategoryDAO categoryDAO;
    private OrderDAO orderDAO;
    private PeriodicOrderDAO periodicOrderDAO;
    private ProductsInOrderDAO productsInOrderDAO;
    private ProductsOfSupplierDAO productsOfSupplierDAO;
    private SupplierDAO supplierDAO;
    private ItemDAO itemDAO;
    private DiscountDAO priceDisDAO;
    private DiscountDAO costDisDAO;
    private SaleDAO saleDAO;
    private FaultyItemDAO faultyItemDAO;

    private static class MapperHolder {
        private static Mapper instance = new Mapper();
    }

    private Mapper() {
        billOfQuantityDAO = new BillOfQuantityDAO();
        categoryDAO = new CategoryDAO();
        orderDAO = new OrderDAO();
        periodicOrderDAO = new PeriodicOrderDAO();
        productsInOrderDAO = new ProductsInOrderDAO();
        productsOfSupplierDAO = new ProductsOfSupplierDAO();
        supplierDAO = new SupplierDAO();
        itemDAO = new ItemDAO();
        priceDisDAO = new DiscountDAO("itemPriceDiscount");
        costDisDAO = new DiscountDAO("itemCostDiscount");
        saleDAO = new SaleDAO();
        faultyItemDAO = new FaultyItemDAO();
    }

    public static Mapper getInstance() {
        return MapperHolder.instance;
    }

    //    public ResponseT<SupplierDTO> getSupplier(Integer supplierID) {
//        if (suppliers.containsKey(supplierID))
//            return new ResponseT<>(suppliers.get(supplierID));
//        ResponseT<SupplierDTO> sup = supplierDAO.get(supplierID);
//        if (!sup.ErrorOccured())
//            suppliers.put(supplierID, sup.value);
//        return sup;
//    }
    public ResponseT<List<SupplierCard>> loadSupplierCard() {
        ResponseT<List<SupplierDTO>> supplierRes = supplierDAO.read();
        List<SupplierCard> res = new LinkedList<>();
        if (!supplierRes.ErrorOccured()) {
            for (SupplierDTO dbSupplier : supplierRes.value) {
                res.add(new SupplierCard(dbSupplier.getName(), dbSupplier.getID(), dbSupplier.getAddress(),
                        dbSupplier.getEmail(), dbSupplier.getBankAcc(), dbSupplier.getPaymentMethod(),
                        dbSupplier.getContacts(), dbSupplier.getInfoSupDay(), dbSupplier.isPickUp()));
            }
        } else {
            return new ResponseT<>("Could not load Supplier cards");
        }
        return new ResponseT<>(res);
    }

    public ResponseT<HashMap<Integer,BillOfQuantities>> loadBillsOfQuantity() {
        ResponseT<List<BillOfQuantityDTO>> billRes = billOfQuantityDAO.read();
        HashMap<Integer,BillOfQuantities> res = new HashMap<>();
        if (!billRes.ErrorOccured()) {
            for (BillOfQuantityDTO dbBill : billRes.value) {
                int supplierID = dbBill.getSupplierID();
                if (res.containsKey(supplierID)) {
                    res.get(supplierID).getMinQuantityForDis().put(dbBill.getProductID(), dbBill.getMinQuantity());
                    res.get(supplierID).getDiscountList().put(dbBill.getProductID(), dbBill.getPercentDiscount());
                } else {
                    HashMap<Integer, Integer> min = new HashMap<>(dbBill.getProductID(), dbBill.getMinQuantity());
                    HashMap<Integer, Integer> dis = new HashMap<>(dbBill.getProductID(), dbBill.getPercentDiscount());
                    res.put(supplierID, new BillOfQuantities(min, dis));
                }
            }
        } else {
            return new ResponseT<>("Could not load Bills Of Quantity");
        }
        return new ResponseT<>(res);
    }

    public ResponseT<List<Order>> loadOrders() {
        ResponseT<List<OrderDTO>> orderRes = orderDAO.read();
        List<Order> res = new LinkedList<>();
        if (!orderRes.ErrorOccured()) {
            for (OrderDTO dbOrder : orderRes.value) {
                HashMap<Integer,Integer> productsInOrder = productsInOrderDAO.getProductsFromOrder(dbOrder.getOrderID()).value; //all products from specific order
                res.add(new Order(dbOrder.getOrderID(), dbOrder.getSupplierID(), dbOrder.isDelivered(), productsInOrder));
            }
        } else {
            return new ResponseT<>("Could not load orders");
        }
        return new ResponseT<>(res);
    }

    public ResponseT<List<PeriodicOrder>> loadPeriodic() {
        ResponseT<List<PeriodicOrderDTO>> perOrderRes = periodicOrderDAO.read();
        List<PeriodicOrder> res = new LinkedList<>();
        if (!perOrderRes.ErrorOccured()) {
            for (PeriodicOrderDTO dbPeriodic : perOrderRes.value) {
                HashMap<Integer,Integer> productsInOrder = periodicOrderDAO.getProductsFromPeriod(dbPeriodic.getOrderID()).value; //all products from specific order
                res.add(new PeriodicOrder(dbPeriodic.getOrderID(), dbPeriodic.getIntervals(), dbPeriodic.getSupplyDate(), productsInOrder));
            }
        } else {
            return new ResponseT<>("Could not load periodic orders");
        }
        return new ResponseT<>(res);
    }

    public ResponseT<List<Product>> loadProducts() {
        ResponseT<List<ProductsOfSupplierDTO>> productRes = productsOfSupplierDAO.read();
        List<Product> res = new LinkedList<>();
        if (!productRes.ErrorOccured()) {
            for (ProductsOfSupplierDTO dbProdOfSupp : productRes.value) {
                res.add(new Product(dbProdOfSupp.getProductID(), dbProdOfSupp.getSupplierID(), dbProdOfSupp.getName(),
                        dbProdOfSupp.getCategory(),dbProdOfSupp.getPrice()));
            }
        } else {
            return new ResponseT<>("Could not load orders");
        }
        return new ResponseT<>(res);
    }


//    public ResponseT<BillOfQuantityDTO> getBillOfQuantity(Integer supplierID) {
//        if (billsOfQuantity.containsKey(supplierID))
//            return new ResponseT<>(billsOfQuantity.get(supplierID));
//        ResponseT<BillOfQuantityDTO> bill = billOfQuantityDAO.get(supplierID);
//        if (!bill.ErrorOccured())
//            billsOfQuantity.put(supplierID, bill.value);
//        return bill;
//    }

//    public ResponseT<OrderDTO> getOrder(Integer orderID) {
//        if (orders.containsKey(orderID))
//            return new ResponseT<>(orders.get(orderID));
//        ResponseT<OrderDTO> order = orderDAO.get(orderID);
//        if (!order.ErrorOccured())
//            orders.put(orderID, order.value);
//        return order;
//    }

    public ResponseT<List<Category>> loadCategory() {
        ResponseT<List<Item>> itemsRes = loadItems();
        ResponseT<List<CategoryDTO>> catRes = categoryDAO.read();
        ResponseT<List<CategoryItemsDTO>> catItemRes = categoryDAO.readCategoryItems();
        ResponseT<List<subCategoriesDTO>> subCatRes = categoryDAO.readSubCategory();
        List<Category> allCats = new LinkedList<>();
        List<Category> firstCats = new LinkedList<>();
        if (!itemsRes.ErrorOccured() && !catRes.ErrorOccured() && !catItemRes.ErrorOccured() && !subCatRes.ErrorOccured()) {
            ResponseT<List<Discount>> disRes = loadDiscounts(itemsRes.value);
            if (disRes.ErrorOccured()) {
                return new ResponseT<>("something went wrong with loading discounts");
            }
            //loading categories
            for (CategoryDTO dbCat : catRes.value) {
                Category cat = new Category(dbCat.getName());
                allCats.add(cat);
                firstCats.add(cat);
            }
            //adding items to categories
            for (CategoryItemsDTO catItem : catItemRes.value) {
                ResponseT<Item> iRes = getItem(catItem.getItemID(), itemsRes.value);
                ResponseT<Category> cRes = getCat(catItem.getCatName(), allCats);
                if (!iRes.ErrorOccured() && !cRes.ErrorOccured()) {
                    cRes.value.addItem(iRes.value);
                }
            }
            //setting SubCategories
            for (subCategoriesDTO subCat : subCatRes.value) {
                ResponseT<Category> father = getCat(subCat.getFatherCategory(), allCats);
                ResponseT<Category> child = getCat(subCat.getChildCategory(), allCats);
                if (!father.ErrorOccured() && !child.ErrorOccured()) {
                    father.value.addSubCategory(child.value);
                    firstCats.remove(getCat(subCat.getChildCategory(), allCats).value);
                } else {
                    return new ResponseT<>("Could not load Categories");
                }
            }
        } else {
            return new ResponseT<>("Could not load Categories");
        }
        return new ResponseT<>(firstCats);
    }

    public ResponseT<List<FaultyItem>> loadFaulty() {
        ResponseT<List<FaultyItemDTO>> faultyRes = faultyItemDAO.read();
        List<FaultyItem> res = new LinkedList<>();
        if (!faultyRes.ErrorOccured()) {
            for (FaultyItemDTO dbFaulty : faultyRes.value) {
                res.add(new FaultyItem(dbFaulty.getItemId(), dbFaulty.getExpDate(), dbFaulty.getAmount()));
            }
        } else {
            return new ResponseT<>("Could not load Faulty items");
        }
        return new ResponseT<>(res);
    }

    public ResponseT<List<Sale>> loadSales() {
        ResponseT<List<SaleDTO>> saleRes = saleDAO.read();
        List<Sale> res = new LinkedList<>();
        if (!saleRes.ErrorOccured()) {
            for (SaleDTO dbSale : saleRes.value) {
                res.add(new Sale(dbSale.getItemID(), dbSale.getCost(), dbSale.getPrice(), dbSale.getDate()));
            }
        } else {
            return new ResponseT<>("Could not load Sales");
        }
        return new ResponseT<>(res);
    }

    private ResponseT<List<Item>> loadItems() {
        ResponseT<List<ItemDTO>> itemsRes = itemDAO.read();
        List<Item> res = new LinkedList<>();
        if (!itemsRes.ErrorOccured()) {
            for (ItemDTO dbItem : itemsRes.value) {
                res.add(new Item(dbItem.getId(), dbItem.getName(), dbItem.getPrice(), dbItem.getCost(), dbItem.getShelfNum(), dbItem.getManufacturer(), dbItem.getShelfQuantity(), dbItem.getStorageQuantity(), dbItem.getMinAlert()));
            }
        } else {
            return new ResponseT<>("Could not load items");
        }
        return new ResponseT<>(res);
    }

    private ResponseT<List<Discount>> loadDiscounts(List<Item> items) {
        ResponseT<List<DiscountDTO>> priceDisRes = priceDisDAO.read();
        ResponseT<List<DiscountDTO>> costDisRes = costDisDAO.read();
        List<Discount> result = new LinkedList<>();
        if (!priceDisRes.ErrorOccured() && !costDisRes.ErrorOccured()) {
            for (DiscountDTO priceDis : priceDisRes.value) {
                Discount toAdd = new Discount(priceDis.getStart(), priceDis.getEnd(), priceDis.getDiscountPr());
                result.add(toAdd);
                ResponseT<Item> iRes = getItem(priceDis.getItemId(), items);
                if (!iRes.ErrorOccured()) {
                    iRes.value.addPriceDiscount(toAdd);
                } else {
                    return new ResponseT<>("Error loading Discounts");
                }
            }
            for (DiscountDTO costDis : costDisRes.value) {
                Discount toAdd = new Discount(costDis.getStart(), costDis.getEnd(), costDis.getDiscountPr());
                result.add(toAdd);
                ResponseT<Item> iRes = getItem(costDis.getItemId(), items);
                if (!iRes.ErrorOccured()) {
                    iRes.value.addCostDiscount(toAdd);
                } else {
                    return new ResponseT<>("Error loading Discounts");
                }
            }
        } else {
            return new ResponseT<>("Error loading Discounts");
        }
        return new ResponseT<>(result);
    }

    private ResponseT<Item> getItem(int id, List<Item> items) {
        for (Item i : items) {
            if (i.getId() == id) {
                return new ResponseT<>(i);
            }
        }
        return new ResponseT<>("Error");
    }

    private ResponseT<Category> getCat(String name, List<Category> cats) {
        if (name == null) {
            return null;
        }
        for (Category c : cats) {
            if (c.getName().equals(name)) {
                return new ResponseT<>(c);
            }
        }
        return new ResponseT<>("Error");
    }

    //for testing purpeses
    public void getItems() {
        ResponseT<List<ItemDTO>> result = itemDAO.read();
        System.out.println(result.value.get(0).getName());
    }

}
