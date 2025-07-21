package ext.org.apache.poi.hpsf;

import org.apache.poi.hpsf.PropertySet;
import org.apache.poi.hpsf.wellknown.PropertyIDMap;
import org.apache.poi.poifs.filesystem.DirectoryEntry;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class HwpPropertySet extends PropertySet {
    /**
     * The id to name mapping of the properties
     *  in this set.
     */
    public PropertyIDMap getPropertySetIDMap() {
        return PropertyIDMap.getSummaryInformationProperties();
    }

    /**
     * <p>The "real" property set <code>HwpPropertySet</code>
     * delegates to.</p>
     */
    private PropertySet delegate;



    /**
     * <p>Creates a <code>HwpPropertySet</code>.
     *
     * @param ps The property set to be encapsulated by the
     * <code>HwpPropertySet</code>
     */
    public HwpPropertySet(final PropertySet ps)
    {
        delegate = new PropertySet(ps);
    }


    /**
     * <p>Creates a <code>HwpPropertySet</code>.
     *
     * @param ps The mutable property set to be encapsulated by the
     * <code>HwpPropertySet</code>
     */
    public HwpPropertySet(final PropertySet ps)
    {
        delegate = ps;
    }



    /**
     * @see PropertySet#getByteOrder
     */
    public int getByteOrder()
    {
        return delegate.getByteOrder();
    }



    /**
     * @see PropertySet#getFormat
     */
    public int getFormat()
    {
        return delegate.getFormat();
    }



    /**
     * @see PropertySet#getOSVersion
     */
    public int getOSVersion()
    {
        return delegate.getOSVersion();
    }



    /**
     * @see PropertySet#getClassID
     */
    public ClassID getClassID()
    {
        return delegate.getClassID();
    }



    /**
     * @see PropertySet#getSectionCount
     */
    public int getSectionCount()
    {
        return delegate.getSectionCount();
    }



    /**
     * @see PropertySet#getSections
     */
    public List getSections()
    {
        return delegate.getSections();
    }



    /**
     * @see PropertySet#isSummaryInformation
     */
    public boolean isSummaryInformation()
    {
        return delegate.isSummaryInformation();
    }



    /**
     * @see PropertySet#isDocumentSummaryInformation
     */
    public boolean isDocumentSummaryInformation()
    {
        return delegate.isDocumentSummaryInformation();
    }



    /**
     * @see PropertySet#getSingleSection
     */
    public Section getFirstSection()
    {
        return delegate.getFirstSection();
    }


    /**
     * @see org.apache.poi.hpsf.PropertySet#addSection(org.apache.poi.hpsf.Section)
     */
    public void addSection(final Section section)
    {
        delegate.addSection(section);
    }



    /**
     * @see org.apache.poi.hpsf.PropertySet#clearSections()
     */
    public void clearSections()
    {
        delegate.clearSections();
    }



    /**
     * @see org.apache.poi.hpsf.PropertySet#setByteOrder(int)
     */
    public void setByteOrder(final int byteOrder)
    {
        delegate.setByteOrder(byteOrder);
    }



    /**
     * @see org.apache.poi.hpsf.PropertySet#setClassID(org.apache.poi.hpsf.ClassID)
     */
    public void setClassID(final ClassID classID)
    {
        delegate.setClassID(classID);
    }



    /**
     * @see org.apache.poi.hpsf.PropertySet#setFormat(int)
     */
    public void setFormat(final int format)
    {
        delegate.setFormat(format);
    }



    /**
     * @see org.apache.poi.hpsf.PropertySet#setOSVersion(int)
     */
    public void setOSVersion(final int osVersion)
    {
        delegate.setOSVersion(osVersion);
    }



    /**
     * @see org.apache.poi.hpsf.PropertySet#toInputStream()
     */
    public InputStream toInputStream() throws IOException, WritingNotSupportedException
    {
        return delegate.toInputStream();
    }



    /**
     * @see org.apache.poi.hpsf.PropertySet#write(org.apache.poi.poifs.filesystem.DirectoryEntry, String)
     */
    public void write(final DirectoryEntry dir, final String name) throws WritingNotSupportedException, IOException
    {
        delegate.write(dir, name);
    }



    /**
     * @see org.apache.poi.hpsf.PropertySet#write(OutputStream)
     */
    public void write(final OutputStream out) throws WritingNotSupportedException, IOException
    {
        delegate.write(out);
    }

    public byte[] toBytes() throws WritingNotSupportedException, IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        delegate.write(bos);
        return bos.toByteArray();
    }


    /**
     * @see org.apache.poi.hpsf.PropertySet#equals(Object)
     */
    public boolean equals(final Object o)
    {
        return delegate.equals(o);
    }



    /**
     * @see org.apache.poi.hpsf.PropertySet#getProperties()
     */
    public Property[] getProperties() throws NoSingleSectionException
    {
        return delegate.getProperties();
    }



    /**
     * @see org.apache.poi.hpsf.PropertySet#getProperty(int)
     */
    protected Object getProperty(final int id) throws NoSingleSectionException
    {
        return delegate.getProperty(id);
    }



    /**
     * @see org.apache.poi.hpsf.PropertySet#getPropertyBooleanValue(int)
     */
    protected boolean getPropertyBooleanValue(final int id) throws NoSingleSectionException
    {
        return delegate.getPropertyBooleanValue(id);
    }



    /**
     * @see org.apache.poi.hpsf.PropertySet#getPropertyIntValue(int)
     */
    protected int getPropertyIntValue(final int id) throws NoSingleSectionException
    {
        return delegate.getPropertyIntValue(id);
    }



    /**
     * @see org.apache.poi.hpsf.PropertySet#hashCode()
     */
    public int hashCode()
    {
        return delegate.hashCode();
    }



    /**
     * @see org.apache.poi.hpsf.PropertySet#toString()
     */
    public String toString()
    {
        return delegate.toString();
    }



    /**
     * @see org.apache.poi.hpsf.PropertySet#wasNull()
     */
    public boolean wasNull() throws NoSingleSectionException
    {
        return delegate.wasNull();
    }
}
