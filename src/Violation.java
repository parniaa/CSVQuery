import java.util.Date;
import java.util.Objects;

public class Violation {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Violation violation = (Violation) o;
        return Objects.equals(violationId, violation.violationId) &&
                Objects.equals(inspectionId, violation.inspectionId) &&
                Objects.equals(violationCategory, violation.violationCategory) &&
                Objects.equals(violationDate, violation.violationDate) &&
                Objects.equals(violationDateClosed, violation.violationDateClosed) &&
                Objects.equals(violationType, violation.violationType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(violationId, inspectionId, violationCategory, violationDate, violationDateClosed, violationType);
    }

    private Integer violationId;
    private Integer inspectionId;
    private String violationCategory;
    private Date violationDate;
    private Date violationDateClosed;
    private String violationType;

    public Violation(Integer violationId, Integer inspectionId, String violationCategory, Date violationDate,
                     Date violationDateClosed, String violationType) {
        this.violationId = violationId;
        this.inspectionId = inspectionId;
        this.violationCategory = violationCategory;
        this.violationDate = violationDate;
        this.violationDateClosed = violationDateClosed;
        this.violationType = violationType;
    }

    public int getViolationId() {

        return violationId;
    }

    public int getInspectionId() {
        return inspectionId;
    }

    public String getViolationCategory() {
        return violationCategory;
    }

    public Date getViolationDate() {
        return violationDate;
    }

    public Date getViolationDateClosed() {
        return violationDateClosed;
    }

    public String getViolationType() {
        return violationType;
    }
}
