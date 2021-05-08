package DataLayer;

import BussinessLayer.Inventory.*;
import BussinessLayer.ResponseT;
import DataLayer.DAO.*;
import DataLayer.DTO.*;

import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Mapper {
        /**
         * This class holds the identity maps of Employees and Shifts.
         * Any access to the data base goes through here.
         */
        private BillOfQuantityDAO billOfQuantityDAO;
        private CategoryDAO categoryDAO;
        private OrderDAO orderDAO;
        private ProductsInOrderDAO productsInOrderDAO;
        private ProductsOfSupplierDAO productsOfSupplierDAO;
        private SupplierDAO supplierDAO;
        private Map<Integer, BillOfQuantityDTO> billsOfQuantity;
        private Map<Integer, OrderDTO> orders;
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
            productsInOrderDAO = new ProductsInOrderDAO();
            productsOfSupplierDAO = new ProductsOfSupplierDAO();
            supplierDAO = new SupplierDAO();
            billsOfQuantity = new HashMap<>();
            orders = new HashMap<>();
            itemDAO = new ItemDAO();
            priceDisDAO = new DiscountDAO("itemPriceDiscount");
            costDisDAO= new DiscountDAO("itemCostDiscount");
            faultyItemDAO = new FaultyItemDAO();
        }

        public static Mapper getInstance(){
            return MapperHolder.instance;
        }

        public ResponseT<BillOfQuantityDTO> getBillOfQuantity(Integer supplierID) {
            if (billsOfQuantity.containsKey(supplierID))
                return new ResponseT<>(billsOfQuantity.get(supplierID));
            ResponseT<BillOfQuantityDTO> bill = billOfQuantityDAO.get(supplierID);
            if (!bill.ErrorOccured())
                billsOfQuantity.put(supplierID, bill.value);
            return bill;
        }

        public ResponseT<OrderDTO> getOrder(Integer orderID) {
            if (orders.containsKey(orderID))
                return new ResponseT<>(orders.get(orderID));
            ResponseT<OrderDTO> order = orderDAO.get(orderID);
            if (!order.ErrorOccured())
                orders.put(orderID, order.value);
            return order;
        }

        public ResponseT<List<Category>> loadCategory() {
            ResponseT<List<Item>> itemsRes = loadItems();
            ResponseT<List<CategoryDTO>> catRes = categoryDAO.read();
            ResponseT<List<CategoryItemsDTO>> catItemRes = categoryDAO.readCategoryItems();
            ResponseT<List<subCategoriesDTO>> subCatRes = categoryDAO.readSubCategory();
            List<Category> res= new LinkedList<>();
            if (!itemsRes.ErrorOccured() && !catRes.ErrorOccured() && !catItemRes.ErrorOccured() && !subCatRes.ErrorOccured()) {
                for(CategoryDTO dbCat : catRes.value) {
                    res.add(new Category(dbCat.getName()));
                }
            } else {
                return new ResponseT<>("Could not load Categories");
            }
            return new ResponseT<>(res);
        }

        public ResponseT<List<FaultyItem>> loadFaulty() {
            ResponseT<List<FaultyItemDTO>> faultyRes = faultyItemDAO.read();
            List<FaultyItem> res= new LinkedList<>();
            if (!faultyRes.ErrorOccured()) {
                for(FaultyItemDTO dbFaulty : faultyRes.value) {
                    res.add(new FaultyItem(dbFaulty.getItemId(), dbFaulty.getExpDate(), dbFaulty.getAmount()));
                }
            } else {
                return new ResponseT<>("Could not load Faulty items");
            }
            return new ResponseT<>(res);
        }

        public ResponseT<List<Sale>> loadSales() {
            ResponseT<List<SaleDTO>> saleRes = saleDAO.read();
            List<Sale> res= new LinkedList<>();
            if (!saleRes.ErrorOccured()) {
                for(SaleDTO dbSale : saleRes.value) {
                    res.add(new Sale(dbSale.getItemID(),dbSale.getCost(), dbSale.getPrice(), dbSale.getDate()));
                }
            } else {
                return new ResponseT<>("Could not load Sales");
            }
            return new ResponseT<>(res);
        }

        private ResponseT<List<Item>> loadItems() {
            ResponseT<List<ItemDTO>> itemsRes = itemDAO.read();
            List<Item> res= new LinkedList<>();
            if (!itemsRes.ErrorOccured()) {
                for(ItemDTO dbItem : itemsRes.value) {
                    res.add(new Item(dbItem.getId(), dbItem.getName(),dbItem.getPrice(),dbItem.getCost(),dbItem.getShelfNum(),dbItem.getManufacturer(),dbItem.getShelfQuantity(), dbItem.getStorageQuantity() ,dbItem.getMinAlert()));
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
                for(DiscountDTO priceDis : priceDisRes.value) {
                    Discount toAdd = new Discount(priceDis.getStart(), priceDis.getEnd(), priceDis.getDiscountPr());
                    result.add(toAdd);
                    ResponseT<Item> iRes = getItem(priceDis.getItemId(), items);
                    if (!iRes.ErrorOccured()) {
                        iRes.value.addPriceDiscount(toAdd);
                    } else {
                        return  new ResponseT<>("Error loading Discounts");
                    }
                }
                for(DiscountDTO costDis : costDisRes.value) {
                    Discount toAdd = new Discount(costDis.getStart(), costDis.getEnd(), costDis.getDiscountPr());
                    result.add(toAdd);
                    ResponseT<Item> iRes = getItem(costDis.getItemId(), items);
                    if (!iRes.ErrorOccured()) {
                        iRes.value.addCostDiscount(toAdd);
                    } else {
                        return  new ResponseT<>("Error loading Discounts");
                    }
                }
            } else {
                return  new ResponseT<>("Error loading Discounts");
            }
            return new ResponseT<>(result);
         }

         private ResponseT<Item> getItem(int id, List<Item> items) {
            for(Item i : items) {
                if(i.getId() == id) {
                    return new ResponseT<>(i);
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
