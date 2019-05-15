package org.zubtsov.linguistics.dictionary.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zubtsov.linguistics.dictionary.entities.characteristics.*;
import org.zubtsov.linguistics.dictionary.entities.utils.WordFormsMutableAttributesMapping;

public class Verb extends MutablePartOfSpeech<Verb.ImmutableAttributes, Verb.MutableAttributes> {
    public static final int MAXIMUM_NUMBER_OF_WORD_FORMS = Наклонение.values().length *
            Время.values().length *
            Род.values().length *
            Число.values().length *
            Лицо.values().length;

    public Verb(String initialForm) {
        this();
        wordFormsMutableAttributesMapping.setWordForm(new Словоформа(initialForm), MutableAttributes.initialFormAttributes);
    }

    public Verb() {
        this(new ImmutableAttributes(), new WordFormsMutableAttributesMapping<>(MAXIMUM_NUMBER_OF_WORD_FORMS, MAXIMUM_NUMBER_OF_WORD_FORMS));
    }

    public Verb(ImmutableAttributes wordImmutableAttributes, WordFormsMutableAttributesMapping<Словоформа, MutableAttributes> wordFormsMutableAttributesMapping) {
        this.wordImmutableAttributes = wordImmutableAttributes;
        this.wordFormsMutableAttributesMapping = wordFormsMutableAttributesMapping;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MutableAttributes { //аналог "грамматического разряда" по А.А. Зализняку
        public static MutableAttributes initialFormAttributes = new MutableAttributes();

        private Наклонение наклонение;
        private Время время;
        private Род род;
        private Число число;
        private Лицо лицо;
    }

    @Data
    public static class ImmutableAttributes {
        private Вид вид;
        private Возвратность возвратность;
        private Переходность переходность;
        private Спряжение спряжение;
    }

    @Override
    public Словоформа getInitialForm() {
        //todo: enforce presence of initial form by means of constructors?
        return wordFormsMutableAttributesMapping.getWordFormByMutableAttributes(MutableAttributes.initialFormAttributes).iterator().next();
    }
}
