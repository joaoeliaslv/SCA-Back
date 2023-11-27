package br.joaoeliaslv.prototipocadastro.util.objectconverter;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;
import java.util.List;

public class ObjectConverterImpl implements ObjectConverter
{
    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public <T, S> S convert(T t, Class<S> s)
    {
        return modelMapper.map(t, s);
    }

    @Override
    public <T, S> List<S> convert(List<T> t, Class<S> s)
    {
        List<S> list = new ArrayList<>(t.size());

        for (T element : t)
        {
            list.add(modelMapper.map(element, s));
        }

        return list;
    }
}
