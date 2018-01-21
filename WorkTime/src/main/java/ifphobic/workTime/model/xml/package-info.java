@XmlJavaTypeAdapters({
@XmlJavaTypeAdapter(value = LocalTimeXmlAdapter.class),
@XmlJavaTypeAdapter(value = LocalDateXmlAdapter.class),
@XmlJavaTypeAdapter(value = DurationXmlAdapter.class)
})
package ifphobic.workTime.model.xml;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import ifphobic.workTime.model.xml.DurationXmlAdapter;
