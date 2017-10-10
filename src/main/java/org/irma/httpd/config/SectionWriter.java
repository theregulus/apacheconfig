package org.irma.httpd.config;

import java.io.IOException;

class SectionWriter extends DirectiveWriter {

    @Override
    public void write(Directive d, TextWriter writer) throws IOException {
    	Section section = (Section) d;
        writer.appendCurrentIndent()
              .append(Constants.LESS_THAN);
        writer.write(section.getName());
        writer.write(writer.getFormatOptions().getArgumentSeparatorPolicy().getCharacter());
        writer.write(section.getArguments().toArray(new String[section.getArguments().size()]));
        writer.append(Constants.GREATER_THAN);
        writer.newLine();
        
        writer.increaseIndent();
        for (Element element : section.getElements()) {
        	ElementWriter elementWriter = element.geElementWriter();
        	elementWriter.write(element, writer);
        }
        writer.decreaseIndent();
        writer.append(Constants.LESS_THAN);
        writer.append(Constants.SLASH);
        writer.write(section.getName());
        writer.append(Constants.GREATER_THAN);
        writer.newLine();
    }
}
