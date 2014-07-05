package net.sf.anathema.scribe.scroll.persistence;

import net.sf.anathema.platform.repository.printname.RepositoryId;
import net.sf.anathema.platform.repository.printname.SimpleRepositoryId;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class InMemoryScrollPersister implements ScrollPersister {

  private int counter = 0;
  private final Map<RepositoryId, Scroll> scrollsByRepositoryId = new HashMap<>();

  @Override
  public void saveScroll(Scroll scroll) {
    RepositoryId repositoryId = scroll.repositoryId;
    scrollsByRepositoryId.put(repositoryId, scroll);
  }

  @Override
  public Scroll loadScroll(RepositoryId id) {
    return scrollsByRepositoryId.get(id);
  }

  @Override
  public Scroll newScroll() {
    ScrollDto newScrollDto = new ScrollDto("", "");
    String id = String.valueOf(counter++);
    return new Scroll(newScrollDto, new SimpleRepositoryId(id));
  }

  @Override
  public Collection<ScrollReference> listAll() {
    Stream<Scroll> scrolls = scrollsByRepositoryId.values().stream();
    return scrolls.map(scroll -> new ScrollReference(scroll.repositoryId, scroll.dto.title)).collect(toList());
  }

  @Override
  public boolean hasAny() {
    return counter != 0;
  }
}