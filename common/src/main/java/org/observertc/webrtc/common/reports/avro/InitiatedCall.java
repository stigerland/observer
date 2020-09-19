/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.observertc.webrtc.common.reports.avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class InitiatedCall extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 7897480183314412225L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"InitiatedCall\",\"namespace\":\"org.observertc.webrtc.common.reports.avro\",\"fields\":[]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<InitiatedCall> ENCODER =
      new BinaryMessageEncoder<InitiatedCall>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<InitiatedCall> DECODER =
      new BinaryMessageDecoder<InitiatedCall>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<InitiatedCall> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<InitiatedCall> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<InitiatedCall> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<InitiatedCall>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this InitiatedCall to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a InitiatedCall from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a InitiatedCall instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static InitiatedCall fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }


  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Creates a new InitiatedCall RecordBuilder.
   * @return A new InitiatedCall RecordBuilder
   */
  public static org.observertc.webrtc.common.reports.avro.InitiatedCall.Builder newBuilder() {
    return new org.observertc.webrtc.common.reports.avro.InitiatedCall.Builder();
  }

  /**
   * Creates a new InitiatedCall RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new InitiatedCall RecordBuilder
   */
  public static org.observertc.webrtc.common.reports.avro.InitiatedCall.Builder newBuilder(org.observertc.webrtc.common.reports.avro.InitiatedCall.Builder other) {
    if (other == null) {
      return new org.observertc.webrtc.common.reports.avro.InitiatedCall.Builder();
    } else {
      return new org.observertc.webrtc.common.reports.avro.InitiatedCall.Builder(other);
    }
  }

  /**
   * Creates a new InitiatedCall RecordBuilder by copying an existing InitiatedCall instance.
   * @param other The existing instance to copy.
   * @return A new InitiatedCall RecordBuilder
   */
  public static org.observertc.webrtc.common.reports.avro.InitiatedCall.Builder newBuilder(org.observertc.webrtc.common.reports.avro.InitiatedCall other) {
    if (other == null) {
      return new org.observertc.webrtc.common.reports.avro.InitiatedCall.Builder();
    } else {
      return new org.observertc.webrtc.common.reports.avro.InitiatedCall.Builder(other);
    }
  }

  /**
   * RecordBuilder for InitiatedCall instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<InitiatedCall>
    implements org.apache.avro.data.RecordBuilder<InitiatedCall> {


    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.observertc.webrtc.common.reports.avro.InitiatedCall.Builder other) {
      super(other);
    }

    /**
     * Creates a Builder by copying an existing InitiatedCall instance
     * @param other The existing instance to copy.
     */
    private Builder(org.observertc.webrtc.common.reports.avro.InitiatedCall other) {
      super(SCHEMA$);
    }

    @Override
    @SuppressWarnings("unchecked")
    public InitiatedCall build() {
      try {
        InitiatedCall record = new InitiatedCall();
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<InitiatedCall>
    WRITER$ = (org.apache.avro.io.DatumWriter<InitiatedCall>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<InitiatedCall>
    READER$ = (org.apache.avro.io.DatumReader<InitiatedCall>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
    } else {
      for (int i = 0; i < 0; i++) {
        switch (fieldOrder[i].pos()) {
        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










