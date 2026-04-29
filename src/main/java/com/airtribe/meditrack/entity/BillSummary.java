import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

import com.airtribe.meditrack.interfaces.Payable;

public final class BillSummary implements Payable{

    private final String billId;
    private final String patientId;
    private final LocalDateTime billDate;

    private final List<Bill> items;     // unmodifiable
    private final BigDecimal subTotal;      // sum of item amounts
    private final BigDecimal tax;           // computed
    private final BigDecimal discount;      // provided
    private final BigDecimal totalAmount;   // subTotal + tax - discount

    // Example: taxRate as percentage (e.g., 0.18 for 18%)
    public BillSummary(String billId,
                       String patientId,
                       LocalDateTime billDate,
                       List<Bill> items,
                       BigDecimal taxRate,
                       BigDecimal discount) {

        this.billId = Objects.requireNonNull(billId);
        this.patientId = Objects.requireNonNull(patientId);
        this.billDate = Objects.requireNonNull(billDate);

        // Defensive copy + unmodifiable
        this.items = Collections.unmodifiableList(new ArrayList<>(Objects.requireNonNull(items)));

        // Calculate subtotal
        this.subTotal = this.items.stream()
                .map(Bill::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Normalize inputs
        BigDecimal safeTaxRate = taxRate == null ? BigDecimal.ZERO : taxRate;
        this.discount = discount == null ? BigDecimal.ZERO : discount;

        // tax = subTotal * taxRate
        this.tax = this.subTotal.multiply(safeTaxRate)
                .setScale(2, RoundingMode.HALF_UP);

        // total = subTotal + tax - discount
        this.totalAmount = this.subTotal
                .add(this.tax)
                .subtract(this.discount)
                .setScale(2, RoundingMode.HALF_UP);
    }

    // Getters (no setters)
    public String getBillId() { return billId; }
    public String getPatientId() { return patientId; }
    public LocalDateTime getBillDate() { return billDate; }
    public List<Bill> getItems() { return items; }
    public BigDecimal getSubTotal() { return subTotal; }
    public BigDecimal getTax() { return tax; }
    public BigDecimal getDiscount() { return discount; }
    public BigDecimal getTotalAmount() { return totalAmount; }

    @Override
    public String toString() {
        return "BillSummary{" +
                "billId='" + billId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", subTotal=" + subTotal +
                ", tax=" + tax +
                ", discount=" + discount +
                ", totalAmount=" + totalAmount +
                '}';
    }

	@Override
	public void pay() {
		// TODO Auto-generated method stub
		
	}
}