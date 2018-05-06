/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package bank;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2018-05-06")
public class LoanCosts implements org.apache.thrift.TBase<LoanCosts, LoanCosts._Fields>, java.io.Serializable, Cloneable, Comparable<LoanCosts> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("LoanCosts");

  private static final org.apache.thrift.protocol.TField NATIVE_CURRENCY_COST_FIELD_DESC = new org.apache.thrift.protocol.TField("nativeCurrencyCost", org.apache.thrift.protocol.TType.DOUBLE, (short)1);
  private static final org.apache.thrift.protocol.TField REQUESTED_CURRENCY_COST_FIELD_DESC = new org.apache.thrift.protocol.TField("requestedCurrencyCost", org.apache.thrift.protocol.TType.DOUBLE, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new LoanCostsStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new LoanCostsTupleSchemeFactory();

  public double nativeCurrencyCost; // required
  public double requestedCurrencyCost; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NATIVE_CURRENCY_COST((short)1, "nativeCurrencyCost"),
    REQUESTED_CURRENCY_COST((short)2, "requestedCurrencyCost");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // NATIVE_CURRENCY_COST
          return NATIVE_CURRENCY_COST;
        case 2: // REQUESTED_CURRENCY_COST
          return REQUESTED_CURRENCY_COST;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __NATIVECURRENCYCOST_ISSET_ID = 0;
  private static final int __REQUESTEDCURRENCYCOST_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NATIVE_CURRENCY_COST, new org.apache.thrift.meta_data.FieldMetaData("nativeCurrencyCost", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.REQUESTED_CURRENCY_COST, new org.apache.thrift.meta_data.FieldMetaData("requestedCurrencyCost", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(LoanCosts.class, metaDataMap);
  }

  public LoanCosts() {
  }

  public LoanCosts(
    double nativeCurrencyCost,
    double requestedCurrencyCost)
  {
    this();
    this.nativeCurrencyCost = nativeCurrencyCost;
    setNativeCurrencyCostIsSet(true);
    this.requestedCurrencyCost = requestedCurrencyCost;
    setRequestedCurrencyCostIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public LoanCosts(LoanCosts other) {
    __isset_bitfield = other.__isset_bitfield;
    this.nativeCurrencyCost = other.nativeCurrencyCost;
    this.requestedCurrencyCost = other.requestedCurrencyCost;
  }

  public LoanCosts deepCopy() {
    return new LoanCosts(this);
  }

  @Override
  public void clear() {
    setNativeCurrencyCostIsSet(false);
    this.nativeCurrencyCost = 0.0;
    setRequestedCurrencyCostIsSet(false);
    this.requestedCurrencyCost = 0.0;
  }

  public double getNativeCurrencyCost() {
    return this.nativeCurrencyCost;
  }

  public LoanCosts setNativeCurrencyCost(double nativeCurrencyCost) {
    this.nativeCurrencyCost = nativeCurrencyCost;
    setNativeCurrencyCostIsSet(true);
    return this;
  }

  public void unsetNativeCurrencyCost() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __NATIVECURRENCYCOST_ISSET_ID);
  }

  /** Returns true if field nativeCurrencyCost is set (has been assigned a value) and false otherwise */
  public boolean isSetNativeCurrencyCost() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __NATIVECURRENCYCOST_ISSET_ID);
  }

  public void setNativeCurrencyCostIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __NATIVECURRENCYCOST_ISSET_ID, value);
  }

  public double getRequestedCurrencyCost() {
    return this.requestedCurrencyCost;
  }

  public LoanCosts setRequestedCurrencyCost(double requestedCurrencyCost) {
    this.requestedCurrencyCost = requestedCurrencyCost;
    setRequestedCurrencyCostIsSet(true);
    return this;
  }

  public void unsetRequestedCurrencyCost() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __REQUESTEDCURRENCYCOST_ISSET_ID);
  }

  /** Returns true if field requestedCurrencyCost is set (has been assigned a value) and false otherwise */
  public boolean isSetRequestedCurrencyCost() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __REQUESTEDCURRENCYCOST_ISSET_ID);
  }

  public void setRequestedCurrencyCostIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __REQUESTEDCURRENCYCOST_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case NATIVE_CURRENCY_COST:
      if (value == null) {
        unsetNativeCurrencyCost();
      } else {
        setNativeCurrencyCost((java.lang.Double)value);
      }
      break;

    case REQUESTED_CURRENCY_COST:
      if (value == null) {
        unsetRequestedCurrencyCost();
      } else {
        setRequestedCurrencyCost((java.lang.Double)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case NATIVE_CURRENCY_COST:
      return getNativeCurrencyCost();

    case REQUESTED_CURRENCY_COST:
      return getRequestedCurrencyCost();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case NATIVE_CURRENCY_COST:
      return isSetNativeCurrencyCost();
    case REQUESTED_CURRENCY_COST:
      return isSetRequestedCurrencyCost();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof LoanCosts)
      return this.equals((LoanCosts)that);
    return false;
  }

  public boolean equals(LoanCosts that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_nativeCurrencyCost = true;
    boolean that_present_nativeCurrencyCost = true;
    if (this_present_nativeCurrencyCost || that_present_nativeCurrencyCost) {
      if (!(this_present_nativeCurrencyCost && that_present_nativeCurrencyCost))
        return false;
      if (this.nativeCurrencyCost != that.nativeCurrencyCost)
        return false;
    }

    boolean this_present_requestedCurrencyCost = true;
    boolean that_present_requestedCurrencyCost = true;
    if (this_present_requestedCurrencyCost || that_present_requestedCurrencyCost) {
      if (!(this_present_requestedCurrencyCost && that_present_requestedCurrencyCost))
        return false;
      if (this.requestedCurrencyCost != that.requestedCurrencyCost)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(nativeCurrencyCost);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(requestedCurrencyCost);

    return hashCode;
  }

  @Override
  public int compareTo(LoanCosts other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetNativeCurrencyCost()).compareTo(other.isSetNativeCurrencyCost());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNativeCurrencyCost()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.nativeCurrencyCost, other.nativeCurrencyCost);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetRequestedCurrencyCost()).compareTo(other.isSetRequestedCurrencyCost());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRequestedCurrencyCost()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.requestedCurrencyCost, other.requestedCurrencyCost);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("LoanCosts(");
    boolean first = true;

    sb.append("nativeCurrencyCost:");
    sb.append(this.nativeCurrencyCost);
    first = false;
    if (!first) sb.append(", ");
    sb.append("requestedCurrencyCost:");
    sb.append(this.requestedCurrencyCost);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class LoanCostsStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public LoanCostsStandardScheme getScheme() {
      return new LoanCostsStandardScheme();
    }
  }

  private static class LoanCostsStandardScheme extends org.apache.thrift.scheme.StandardScheme<LoanCosts> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, LoanCosts struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NATIVE_CURRENCY_COST
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.nativeCurrencyCost = iprot.readDouble();
              struct.setNativeCurrencyCostIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // REQUESTED_CURRENCY_COST
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.requestedCurrencyCost = iprot.readDouble();
              struct.setRequestedCurrencyCostIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, LoanCosts struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(NATIVE_CURRENCY_COST_FIELD_DESC);
      oprot.writeDouble(struct.nativeCurrencyCost);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(REQUESTED_CURRENCY_COST_FIELD_DESC);
      oprot.writeDouble(struct.requestedCurrencyCost);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class LoanCostsTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public LoanCostsTupleScheme getScheme() {
      return new LoanCostsTupleScheme();
    }
  }

  private static class LoanCostsTupleScheme extends org.apache.thrift.scheme.TupleScheme<LoanCosts> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, LoanCosts struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetNativeCurrencyCost()) {
        optionals.set(0);
      }
      if (struct.isSetRequestedCurrencyCost()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetNativeCurrencyCost()) {
        oprot.writeDouble(struct.nativeCurrencyCost);
      }
      if (struct.isSetRequestedCurrencyCost()) {
        oprot.writeDouble(struct.requestedCurrencyCost);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, LoanCosts struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.nativeCurrencyCost = iprot.readDouble();
        struct.setNativeCurrencyCostIsSet(true);
      }
      if (incoming.get(1)) {
        struct.requestedCurrencyCost = iprot.readDouble();
        struct.setRequestedCurrencyCostIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

