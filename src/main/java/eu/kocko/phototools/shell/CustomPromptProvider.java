package eu.kocko.phototools.shell;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomPromptProvider implements PromptProvider {

  @Override
  public AttributedString getPrompt() {
    return new AttributedString("stefi_phototools:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN));
  }

}