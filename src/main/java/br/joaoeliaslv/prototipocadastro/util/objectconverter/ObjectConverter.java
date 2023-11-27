package br.joaoeliaslv.prototipocadastro.util.objectconverter;

import java.util.List;

public interface ObjectConverter
{
    <T, S> S convert(T t, Class<S> s);
    <T, S> List<S> convert(List<T> t, Class<S> s);
}
